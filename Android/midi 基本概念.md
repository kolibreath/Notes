midi 的组成部分

header chunk => {some track trunks}

smf 是什么？
SMF = <header_chunk> + <track_chunk> [+ <track_chunk> ...]

这个是逻辑结构，并且每一个chunk都有三个部分组成：

1.track id : header chunk "MThd" Track chunk "MTrk" 这些也被称作 chunk IDs
2.32 位 (four byte ) unsigned value
3.真正的数据 这些数据是通过chunk id 后面制定的段长度指定的 

Header chunk :
"MThd" 4bytes 或者位16进制形式0x4d546864,处于midi文件的开头

header_length 一直都会是6个字节的长度

format single-track mutiple-track multiple-songs 

n track chunks的数量

division beat tick delta timing 