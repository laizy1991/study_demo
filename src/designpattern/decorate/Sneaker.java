package designpattern.decorate;

/**
 * 破球鞋
 * @author laizy1991@gmail.com
 * @createDate 2015年8月27日
 *
 */
public class Sneaker extends Finery {

    public void show() {
        component.show();
        System.out.println("破球鞋");
    }
    
}
