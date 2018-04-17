[running status](https://www.midikits.net/midi_analyser/running_status.htm)

midi 的组成部分

header chunk => {some track trunks}

smf 是什么？
standard midi file
SMF = <header_chunk> + <track_chunk> [+ <track_chunk> ...]

这个是逻辑结构，并且每一个chunk都有三个部分组成：

1.track id : header chunk "MThd" Track chunk "MTrk" 这些也被称作 chunk IDs
2.32 位 (four byte ) unsigned value
3.真正的数据 这些数据是通过chunk id 后面制定的段长度指定的 

Header chunk :
  header_chunk = "MThd" + <header_length> + <format> + <n> + <division>

"MThd" 4bytes 或者位16进制形式0x4d546864,处于midi文件的开头

header_length 一直都会是6个字节的长度

format single-track mutiple-track multiple-songs 

n track chunks的数量

division beat tick delta timing 2bytes


Track Chunk 
 track_chunk = "MTrk" + <length> + <track_event> [+ <track_event> ...]

 length 也是 4个字节
 running status；简要老说 就是 省略掉status byte

 使用MTrk开头 后面会接上 track event

   track_event = <v_time> + <midi_event> | <meta_event> | <sysex_event>
 

 track event 会包含这三种event之一

<v_time>
a variable length value specifying the elapsed time (delta time) from the previous event to this event.
<midi_event>
any MIDI channel message such as note-on or note-off. Running status is used in the same manner as it is used between MIDI devices.
<meta_event>
an SMF meta event.
<sysex_event>
an SMF system exclusive event.

midi event note-off note-on
meta event 十一组非midi的信息 比如说 版权信息 歌词 乐器名
system exclusive event 


The tracks are established within the MIDI software on your computer you are using. Most MIDI recording software advertises that it can have 128, 265, or unlimited MIDI tracks.

Another thing that can make this more confusing is that track one is not necessarily channel one. You have to specify with MIDI channels you are using per track. Refer to your software documentation on how to do this.

It is also possible to have more than one track using the same MIDI channel. For example, say you want to record a piano part for your composition. You may decide to have the right hand part on one track and the left hand on another.


midi Channels 
普遍的 midi 导线只提供了5根针


[note on ](http://www.tonalsoft.com/pub/pitch-bend/pitch.2005-08-31.17-00.aspx)
note on message 
format 
start code + channel number + key number + velocity

1001 选择一个channel 选择一个发生的乐器具体对应的号码由制造商确定 + 响度


note off message

类似 但是start code 是1000

[详细的表格 包含了所有的note的start code](https://www.midi.org/specifications/item/table-1-summary-of-midi-message)