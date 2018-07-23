# install wps in Ubuntu

- installing associating dependencies

````
sudo apt-get install lib32ncurses5 
sudo apt-get install lib32z1
````

- download another dependency from this site
[site](https://packages.debian.org/zh-cn/wheezy/amd64/libpng12-0/download)

- download wps
[location](http://community.wps.cn/download/)

- download fonts for wps
[some fonts](https://pan.baidu.com/s/1mh0lcbY)


change dir to the dir of the fonts
````
sudo cp * /usr/share/fonts

sudo mkfontscale

sudo mkfontdir

sudo fc-cache
````

	
