<!-- PROJECT HEADER -->
<p align="center">
  <!--PROJECT TITLE AND DESCRIPTION -->
 <h1 align="center">Twitter automation using Selenium Java</h1>

  <p align="center">
    Just Another web automation project 😁😁
    <br /><br/>
    <a href="https://www.linkedin.com/in/bishwapoudel/">
        <img src="https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555"
            alt="follow on Twitter"></a>
    <a href="https://twitter.com/intent/follow?screen_name=_bishwapoudel">
        <img src="https://img.shields.io/twitter/follow/_bishwapoudel?style=for-the-badge&logo=twitter"
            alt="follow on LinkedIn"></a>

  </p>
</p>
<hr>

<!-- ABOUT THE PROJECT -->

## About The Project
I have had some issues regarding the application of Twitter developer account, as it was constantly being rejected citing minor issues. At last I finally geared up doing the same, but using different approach. This project explores Selenium as a Web Automation tool that automates Twitter Login, Logout and Likes and can serve as a base for doing other automations in twitter. 

### Disclaimer
This project is purely intended for educational purpose with not to be used in production environment. Many actions automated here goes against Twitter policy, and this project by any means doesn't intend to violate those policies. Please go through Twitter automation policies before actually using this code. Link: [Twitter Developer Policy](https://developer.twitter.com/en/developer-terms/agreement-and-policy)

### Built With
* [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) as programming language
* [Maven](https://maven.apache.org/) for Package Management and Build Automation
* [Selenium](https://www.selenium.dev/) for Web Automation
* [Resteasy](https://resteasy.github.io/) as JAX-RS implementation for Restful Services 

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
  Basic understanding of Java with Maven.

### Running the project
Steps are listed below to get this project up and running in your development environment.
* Setup Java with JDK 1.8. For reference: Use this official [Tutorial](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-microsoft-windows-platforms.htm)
* Setup Maven. Follow this link: [Maven Setup](https://maven.apache.org/install.html) 
* Clone this repository in your target folder.
```
git clone https://github.com/bishwa-poudel/twitter-automation-using-selenium-java.git
```
* Now go to `src/main/resources/twitter.sample.properties` and rename the configuration file to `twitter.properties`. And fill the params listed in properties file.



Example:
```
# file: twitter.properties
#******************************** Twitter account creds ********************************
USERNAME=your_username
PASSWORD=your_password


#******************************** Automation Rate ********************************
LIKE_LIMIT=100
DELAY_IN_MS=3000
REPEAT_AFTER_IN_MINUTES=180


#************************ Twitter Feed URL (with any filters or hashtags) ********************************
TWITTER_FEED_URL=twitter_feed_url
```

1. <b>USERNAME:</b> Your twitter account username 
2. <b>PASSWORD:</b> Your twitter account password (Credentials are purely used for login to twitter.)
3. <b>LIKE_LIMIT:</b> Number of likes that should be done in one cycle.
4. <b>DELAY_IN_MS:</b> You donot want to spam twitter with multiple requests at a single time right. To simulate the human behavior, we are adding some delay after every action in automation.
5. <b>REPEAT_AFTER_IN_MINUTES:</b> The time interval at which the particular action is to be repeated. Ex: 180 denotes Like action will repeat in every 180 minutes
6. <b>TWITTER_FEED_URL:</b> URL of twitter timeline. This might be twitter homepage, timeline of particular hashtags or using any advanced filtering. The URL should open to the tweets timeline. 

* After you are sure that everything is setup correctly, run maven build command:

```
mvn clean install
```

![image](https://user-images.githubusercontent.com/16562819/108626213-270c9b80-7477-11eb-8988-d36f9f60f0fc.png)

* Once you see this message in the console, your build is ready. Goto `target/lis-intranet-automation.war}` to get the archived file.

_Note: I have used WAR for packaging. If you want to build into the jar file, change the `<packaging>` inside pom.xml file to jar._

```
 <packaging>jar</packaging>
```

Now you can use any application server to deploy the archived java file. For this project I am using [Wildfly v22.0.1](https://www.wildfly.org/) 

Congrats !!! You are now able to run the project in your own development environment. 

## Usage
Server automatically schedules intranet check in and check out time for the next day in every restart. There is also APIs exposed for manual check in and check out.

### For Manual Check in
```http
GET /lis-intranet-automation/api/action/checkin
```

### For Manual Checkout
```http
GET /lis-intranet-automation/api/action/checkout
```

## Authors

* **Bishwa Poudel** - *Initial work* - [Bishwa Poudel](https://github.com/bishwa-poudel)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


## Contact
[![LinkedIn][linkedin-shield]][linkedin-url] [![Twitter][twitter-shield]][twitter-url]

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/bishwapoudel/
[twitter-shield]: https://img.shields.io/twitter/url/https/twitter.com/cloudposse.svg?style=for-the-badge&logo=twitter&colorB=555&label=Twitter
[twitter-url]: https://twitter.com/_bishwapoudel
