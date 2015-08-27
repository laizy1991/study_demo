package designpattern.decorate;

/**
 * 服装
 * @author laizy1991@gmail.com
 * @createDate 2015年8月27日
 *
 */
public class Finery extends Person{

    protected Person component;
    
    /**
     * 装饰
     * @param component
     */
    public void decorate(Person component) {
        this.component = component;
    }
    
    public void show() {
        if(component != null) {
            component.show();
        }
    }
}
