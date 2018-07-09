# Common Commands

- ng serve -o
默认的Angular服务器运行在localhost:4200 上

- ng new angular-tour-of-heroes
ng new 生成新的project


# Angular Structure

- ng new testA
之后会生成相关联的typeScript class 和 模板 还有对应的css文件
会生成一个AppComponent 这个AppComponent是最基本的 壳子，
最先被接触的第一个组件

- 壳组件作用：
添加路由 ：
````
<h1>{{title}}</h1>
<nav>
  <a routerLink="/dashboard">Dashboard</a>
  <a routerLink="/heroes">Heroes</a>
</nav>
<router-outlet></router-outlet>
<app-messages></app-messages>
````
- 添加组件
在下面添加命名空间的名字即可

## Angular Component
- ng generate Component heroes
默认会在app 这个module下面创建

## @Component 装饰器
````
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
````
@Component 生成Angular所需要的元数据

目的
- 绑定 templateUrl = 绑定模板文件的位置
       styleUrls  = 绑定css 文件的样式表
- 创建一个包/命名空间 select1 就是会在后面的html中引用的标签

在外壳的模板中就可以引用这个命名空间中的代码

这样做合理分层

## FormsModule
顶级AppModule的元数据，
可以配合ngModel做到双向流动
````
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent
  ],
  imports: [
    FormsModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

````

````
<h2>{{ hero.name }} Details</h2>
<div><span>id: </span>{{hero.id}}</div>
<div><span>name: </span>{{hero.name}}</div>


<div>
  <label>name:
    <input [(ngModel)]="hero.name" placeholder="name">
  </label>
</div>

````
这种重要的全局module一定要声明在 @ngModel , 声明在全局modul中的还有后一些组件的声明：

通过ngModel完成 数据的双向流动

````
import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import {FormsModule} from '@angular/forms';

还需要声明在declarations 数组中

declarations: [
  AppComponent,
  HeroesComponent
],
````

## 事件绑定
````
<li *ngFor="let hero of heroes" (click)="onSelect(hero)">
````


# 主从组件
子组件的 @Input 装饰器
父组件 引用子组件的属性
@input 显示detail的职责交给子组件

# @Injectable 依赖注入
使用ng generate service 生成数据产生逻辑
依赖是如何注入到需要的类中？

````
constructor(private heroService: heroService)
````
## lifeCycle Hook
````
ngOnInit{
  this.getHeroes();
}
````

#路由组件

ng generate module ...

在路由组件中定义路由引导
const routes: Routes = [
  { path: 'heroes', component: HeroesComponent }
];

````
@NgModule({
  注意这里需要exports
    exports: [ RouterModule ]
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
````

路由组件通过路由数组管理路由 ，通过在NgModule装饰器中声明路由，实例化路由forRoot() 初始化路由，通过export RouterModule在外部可以声明路由的命名空间

在ng中初始化 路由

## 路由出口
路由的命名空间默认为<router-outlet></router-outlet>

## 提取路由参数
依赖注入


# Angular Http
