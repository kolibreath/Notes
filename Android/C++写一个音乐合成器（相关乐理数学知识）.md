合成器源码分析：
基础MIDI编程。
找出为什么可以发出声音！只是用了midi标准而已
``SynthesizerService``


MessgeOutPutProcessor 
- 构造函数 ：
初始化`` buff_``(ByteOutPutStream)对象

- 自定义函数：
abstract OnMessage()

- void notifyMessage():调用onMessage()的实现，将buff_，转化为byte[]

- setVelocitySensitivity() velSens_ = 0.5 velAvg = 0.5

*****
乐理部分

[超牛逼的编程视频](https://www.youtube.com/watch?v=tgamhuQnOkM&t=33s)
声音都是由正弦函数组成的，
正弦函数进行算数运算可以得到所有类型的声音。
电脑没有办法模拟正弦函数那样平滑的曲线，只能细分，将这些曲线分割成小块。近似模拟法
如果bit越多，模拟的数据就越准确，2 bits 就可以表示四个数据

但是事实上，在while(1)的时候，直接使用正弦函数的top-bottom 可以得到同样的效果；
这样思考一下，其实合成器中的声音也是这样产生的

噪声来源：
一个正弦函数使用square 模拟，但是speaker cone 扬声器纸盆不能同时在一个时间节点从一个弧到0
，同样的，声音的产生也不能直接从0 到一个最低

振荡器，产生周期性。
有很多很多函数可以产生类似于正弦函数的效果，比如说可以使用square方法将正弦函数分为top-bottom，可以使用sawtooth函数拟合，写一个三角形函数等等。

[envelope of sound](https://www.youtube.com/watch?v=Q-ot9AaJx-Y)
envelope of sound ： the sound get louder or softer over time ADSR
A: attack the sound from generating until it gets the highest pitch
D：the sound droping 
S: sustain the sound gets more stable
R: droping to silence

要注意Envelope的生命周期：
通过这个就可以做成淡入淡出的音乐了