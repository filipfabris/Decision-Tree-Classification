# Decision Tree implementation

## Implementation of:
+ ID3

## Arguments from command line: 
+ Path to the train dataset 
+ Path to the test dataset 
+ Depth of ID3 tree (optional)

## Example
```java -cp target/classesui.Solution example/volleyball.csv example/volleyball_test.csv1```

## File input
* Train data
```
weather,temperature,humidity,wind,play
sunny,hot,high,weak,no
sunny,hot,high,strong,no
cloudy,hot,high,weak,yes
rainy,comfortable,high,weak,yes
rainy,cold,normal,weak,yes
rainy,cold,normal,strong,no
cloudy,cold,normal,strong,yes
sunny,comfortable,high,weak,no
sunny,cold,normal,weak,yes
rainy,comfortable,normal,weak,yes
sunny,comfortable,normal,strong,yes
cloudy,comfortable,high,strong,yes
cloudy,hot,normal,weak,yes
rainy,comfortable,high,strong,no
```
* Test data
```
weather,temperature,humidity,wind,play
rainy,hot,normal,weak,yes
sunny,hot,normal,strong,yes
sunny,hot,normal,weak,yes
cloudy,hot,normal,strong,yes
rainy,cold,normal,strong,yes
sunny,comfortable,normal,weak,yes
cloudy,comfortable,normal,strong,yes
cloudy,comfortable,normal,weak,yes
rainy,hot,high,strong,no
rainy,hot,high,weak,no
cloudy,hot,high,weak,no
rainy,cold,high,strong,no
rainy,cold,high,weak,no
sunny,cold,high,strong,no
sunny,cold,high,weak,no
cloudy,cold,high,strong,no
cloudy,cold,high,weak,no
rainy,comfortable,high,weak,no
cloudy,comfortable,high,strong,no
```

## Ou9tput
```
IG(weather)=0.2467 IG(humidity)=0.1518 IG(wind)=0.0481 IG(temperature)=0.0292
IG(wind)=0.9710 IG(humidity)=0.0200 IG(temperature)=0.0200
IG(humidity)=0.9710 IG(temperature)=0.5710 IG(wind)=0.0200
[BRANCHES]:
1:weather=cloudy yes
1:weather=rainy 2:wind=strong no
1:weather=rainy 2:wind=weak yes
1:weather=sunny 2:humidity=high no
1:weather=sunny 2:humidity=normal yes
[PREDICTIONS]: yes yes yes yes no yes yes yes no yes yes no yes no no yes yes
yes yes
[ACCURACY]: 0.57895
[CONFUSION_MATRIX]:
4 7
1 7
```
