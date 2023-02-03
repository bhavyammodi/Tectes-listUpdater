import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
// For testing purposes
        List<obj> lst = new ArrayList<>();
        lst.add(new obj(0, 0, false, "cell(1)- range[2,4]+cell(5)"));
        lst.add(new obj(1, 0, false, "range[2,2]"));
        lst.add(new obj(2, 20, false, null));
        lst.add(new obj(3, 10, false, null));
        lst.add(new obj(4, 0, false, "cell(3) - range(2,3)"));
        lst.add(new obj(5, 0, false, "cell(4)"));
        lst.add(new obj(6, 10, false, null));
        rangeList rl = new rangeList(lst);
        rl.post();
        /*
        // Output of temp test
            for (obj ob : rl.lst) {
                System.out.print(ob.ind + " -> " + ob.getValue() + " -> ");
                for (int x : ob.getParents()) System.out.print(x + " ");
                System.out.println();
            }
             System.out.println("Updated");
            rl.update(0, 0, "cell(1)- range[2,4]+cell(6)");
            for (obj ob : rl.lst) {
                System.out.print(ob.ind + " -> " + ob.getValue() + " -> ");
                for (int x : ob.getParents()) System.out.print(x + " ");
                System.out.println();
            }
        */
    }
}