# PlayStoreParallelDownload
Play store downloads/updates app in sequence because of the performance issues but still there are the cases when you need to download/upload things in parallel like AppStore or WhatsApp.

# Problem Statement
<ul>
<li>Download apps in parallel.</li>
<li>Update their progress in recyclerview item.</li>
</ul>

# Solution

It can be achieved through using:
<ul>
<li><b>Service </b> - For not messing with the UI</li>
<li><b>AsyncTask </b> - Separate worker thread inside service.</li>
<li><b>THREAD_POOL_EXECUTOR</b> - Feature of AsyncTask to run multiple asynctasks in different threads.</li>
<li><b>LocalBroadcastManager </b>- To notify about the progress of download.</li>
</ul>

# Download

<a href="https://raw.githubusercontent.com/webianks/PlayStoreParallelDownload/master/apk/app-debug.apk"><img src="https://github.com/webianks/HatkeMessenger/blob/master/screens/download.png" height="74" width="50"></a>

# License
```
MIT License

Copyright (c) 2017 Ramankit Singh

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
