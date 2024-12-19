## Overview
This is a simple RSS Reader built with Scala. The application has a GUI using ScalaFX and a backend to fetch and display RSS feed titles.

## Features
- Enter an RSS feed URL.
- Fetch and display feed titles in a list.

## Requirements
- Scala 2.13+
- sbt (Scala Build Tool)
- Java 8+

## How to Run
1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd rss_reader_scala
   ```
2. Run the application using sbt:
   ```bash
   sbt run
   ```
3. Enter an RSS feed URL into the text field and click "Fetch" to see the feed titles.

## Dependencies
- **ScalaFX:** For the graphical user interface.
- **scalaj-http:** For fetching RSS feed data.

## File Structure
- `src/main/scala/RssReaderApp.scala`: Main application file.
- `README.md`: Project documentation.
- `.gitignore`: Specifies ignored files for Git.

## Future Enhancements
- Add error handling for invalid URLs.
- Display more feed details (e.g., descriptions, links).
- Persist feed history.
