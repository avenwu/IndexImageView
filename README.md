IndexImageView
==============

A kind of circular image widget for Android OS.

	#clone
	git clone https://github.com/avenwu/IndexImageView.git

![Screenshot](https://github.com/avenwu/IndexImageView/raw/master/device-2014-09-22-231400.png)

## Features
The main feature can be seen from the snapshot above; 

- Set Text index above the image view;
- Add configured border;
- Inner gradient available;

## Usage
Import the library into Android Studio or add dependency in build.gradle

    compile 'com.github.avenwu:IndexImageView:1.0.1'

Most attributes of IndexImageView can be configured through xml;
    
    <com.avenwu.imageview.IndexImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/image1"
        android:scaleType="centerCrop"
        app:indexText="100"
        app:indexFontSize="20sp"
        app:indexBackground="#ff5722"
        app:gradientEnable="false"
        app:circleBackground="#cddc39"
        app:strokeColor="#259b24" />
		
However you can also make new instance dynamically;

    IndexImageView imageView = new IndexImageView(this);
    imageView.setImageResource(R.drawable.image1);
    imageView.setIndexEnable(true);
    imageView.setText("121");
    
For more detail please look into the sample app;

##Contributions
Any improvemet on this project will be appreaciated, so you can fork it and make changes freely and pull requests.

* Email:  <chaobinwu89@gmail.com>
* Wiki: <https://github.com/avenwu/IndexImageView/wiki>
* Bug Report: <https://github.com/avenwu/IndexImageView/issues>

## License
The MIT License (MIT)

Copyright (c) 2014 Chaobin Wu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.