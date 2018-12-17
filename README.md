# Direction-Line
Library designed to display an array of arrows that can be adjusted direction and color. Can be used for a variety of uses, directions of a particular object at different times, addition to the graph, etc.


![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
[![](https://jitpack.io/v/guy-4444/ExternalLogger-Android.svg)](https://jitpack.io/#guy-4444/Direction-Line)
[![API](https://img.shields.io/badge/API-18%2B-green.svg?style=flat)]()

Vertical and horizontal step line indicator.


![device-2018-06-06-144912](https://github.com/guy-4444/Direction-Line/blob/master/Screenshot.jpg?raw=true)


## Setup
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency:

```
dependencies {
    implementation 'com.github.guy-4444:Direction-Line:1.01.04'
}
```
## Usage

### Direction-Line Examples:

**360 angle colorful direction line:**
```java
final int SIZE = 30;
ArrayList<Integer> nums = new ArrayList<>();
for (int i = 0; i < SIZE; i++) {
    nums.add((int) (i*(360 / SIZE)));
}

directionLineLayout1.setStepLines(this, DirectionLineLayout.LayoutOrientation.HORIZONTAL, 2, SIZE, R.color.skv_arrow_color, 80, R.drawable.ic_arrow);
for (int i = 0; i < directionLineLayout1.getSize(); i++) {
    directionLineLayout1.setUnitDirection(i, nums.get(i));
    int color = Color.HSVToColor(255, new float[]{(float) nums.get(i), 1.0f, 1.0f});
    directionLineLayout1.setUnitColor(i, (int) color);
}
```

**720 angle red-blue direction line:**
```java
        final int SIZE = 60;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            nums.add((int) (i*(720.0 / SIZE)));
        }

        directionLineLayout2.setStepLines(this, DirectionLineLayout.LayoutOrientation.HORIZONTAL, 1, 60, R.color.skv_arrow_color, 40, R.drawable.ic_arrow);
        for (int i = 0; i < directionLineLayout2.getSize(); i++) {
            double yaw = nums.get(i);
            directionLineLayout2.setUnitDirection(i, (int) yaw);
            double X = Math.abs(Math.sin(yaw/360.0*Math.PI)) * 255;
            int color = Color.rgb((int) X, 0, (int) (255-X));
            directionLineLayout2.setUnitColor(i, color);
        }
```



## Credits

Left arrow free icon from Roundicons, flaticon.com

## License

    Copyright 2018 Guy Isakov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
