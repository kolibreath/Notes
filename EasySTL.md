cmakelist 
cmake
makefile

# EasySTL

### iterator 
bidirectional_tag 
### 红黑树iterator:

- 定义了结构体:__rb_tree_node_base
实现了两个找到最左的子树 和 最右边子树 的方法
```
 typedef __rb_tree_color_type color_type;
 typedef __rb_tree_node_base* base_ptr;
```


- 定义了模板结构 __rb_tree_node 继承 __rb_tree_node_base ,重命名为
将 __rb_tre_iterator指针 重命名为link_type

- 定义迭代器类型 __rb_tree_base_iterator
实现两个方法:
increment() 找到这个当前的node最近的一个节点
暂时还不清楚!!!

- 最终定义出来了一个 __rb_tree_iterator 类,这个类封装了一系列的操作符


### rbtree.h

模板参数:
template <class Key, class Value, class KeyOfValue, class Compare,
          class Alloc = alloc>

定义rbtree class 
````
	typedef void* void_pointer;
	typedef __rb_tree_node_base* base_ptr;
	typedef __rb_tree_node<Value> rb_tree_node; 
	typedef simple_alloc<rb_tree_node, Alloc> rb_tree_node_allocator;
````

````
link_type get_node() {return rb_tree_node_allocator::allocate();}
````
返回 rbtree大小的一个新建的node

````
void put_node(link_type p) {rb_tree_node_allocator::deallocate(p);}
```
删除大于   enum {_MAX_BYTES = 128}; //小型区块的上限 的node