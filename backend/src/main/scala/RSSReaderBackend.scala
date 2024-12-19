import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.ember.server._
import org.http4s.server.middleware.CORS
import org.http4s.server.middleware.CORSConfig
import scala.xml.XML
import scala.concurrent.duration._
import com.comcast.ip4s._

object RSSReaderBackend extends IOApp {

  def fetchRSS(url: String): IO[String] = IO {
    val source = scala.io.Source.fromURL(url)
    val rssFeed = source.mkString
    source.close()
    rssFeed
  }

  def parseRSS(xmlString: String): String = {
    val xml = XML.loadString(xmlString)
    (xml \\ "item").map(item => (item \ "title").text).mkString("\n")
  }

  val service = HttpRoutes.of[IO] {
    case GET -> Root / "fetch" / url =>
      fetchRSS(java.net.URLDecoder.decode(url, "UTF-8"))
        .map(parseRSS)
        .flatMap(parsedRss => Ok(parsedRss))
  }

  val corsConfig = CORSConfig.default
    .withAllowedOrigins(_ => true) // Allow all origins
    .withAllowedMethods(Some(Set(Method.GET))) // Allow GET method

  val corsService = CORS(service, corsConfig).orNotFound

  override def run(args: List[String]): IO[ExitCode] = {
    EmberServerBuilder.default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(corsService)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
