# PathEffectTextView [![Download](https://api.bintray.com/packages/alokvnair/maven/PathEffectTextView/images/download.svg)](https://bintray.com/alokvnair/maven/PathEffectTextView/_latestVersion)

TextView with path tracing effect.

![PathEffectTextView](https://raw.githubusercontent.com/alokvnair/PathEffectTextView/master/screens/pathEffective.gif)

###Usage
has three attribute `text_color`, `stroke_width`, `multiple`

```xml
<com.alokvnair.patheffect.PathEffectTextView
        android:id="@+id/path"
        app:stroke_width="2"
        app:text_color="#E91E63"
        app:multiple="false"
        android:layout_width="40dp"
        android:layout_height="40dp"/>
```

`text_color` is for setting color of the text. Default value is Black.
`stroke_width` is for setting width of text strokes. Default value is 2. Value suggested is within the range 2 to 8.
`multiple` is for setting whether to draw path for all characters in text at once or character by character. If `true`, then path of all characters in text will be drawn together. If `false`, then text will be drawn character by character from first to last. Default value is false.

### License

```
The MIT License (MIT)

Copyright (c) 2015 Alok Nair

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
