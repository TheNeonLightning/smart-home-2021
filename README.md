# Smart Home

## Parts of the project

- The main part of the project lies in the folder [src/main/java/ru/sbt/mipt/oop](https://github.com/TheNeonLightning/smart-home-2021/tree/master/src/main/java/ru/sbt/mipt/oop)

- Training "external" library for work with equipment and sensors [src/main/java/com/coolcompany/smarthome](https://github.com/TheNeonLightning/smart-home-2021/tree/master/src/main/java/com/coolcompany/smarthome)

- Training "external" library for remote control [src/main/java/rc](https://github.com/TheNeonLightning/smart-home-2021/tree/master/src/main/java/rc)

## About
Educational project for the practice of software design. Implements a "brain" of a smart home that:
- Monitors the current state of the house via sensors 
- Sends commands to lights (ON/OFF), doors (OPEN/CLOSE) in specific scenarios
- Controls signalization
- Runs owner notification duty
- Supports remote control 

## Specific design solutions
- Implemented traversing of the structure of the house using patterns __Iterator__ and __Composite__
- Implemented signalization using pattern __State__
- Added signalization related event processing logic to existing event processors via __Decorator__
- Connected an external API to the project using the __Adapter__ pattern 
- Worked with dependency injection framework Spring
- Applied external library for remote control to the project usign __Command__ pattern
