package designpattern.decorate;

/**
 * 垮裤
 * @author laizy1991@gmail.com
 * @createDate 2015年8月27日
 *
 */
public class BigTrouser extends Finery {

    public void show() {
        component.show();
        System.out.println("垮裤");
    }
    
}
