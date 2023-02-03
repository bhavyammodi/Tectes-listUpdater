import java.util.*;

public class rangeList {

    List<obj> lst;

    public rangeList(List<obj> lst) {
        this.lst = lst;
    }

    int getValue(obj ob) {
        // recursion
        if (ob.isUpdated()) return ob.getValue();
        List<String> form = ob.formulaDeserialized;
        char prev = '+';
        int val = 0;
        for (int i = 0; i < form.size(); i++) {
            if (form.get(i).equalsIgnoreCase("cell")) {
                int thisval = getValue(lst.get(Integer.parseInt(form.get(i + 1))));
                if (prev == '+') val += thisval;
                if (prev == '-') val -= thisval;
                i++;
            } else if (form.get(i).equalsIgnoreCase("range")) {
                int thisval = 0;
                int a = Integer.parseInt(form.get(i + 1));
                int b = Integer.parseInt(form.get(i + 2));
                for (int x = a; x <= b; x++) thisval += getValue(lst.get(x));
                if (prev == '+') val += thisval;
                if (prev == '-') val -= thisval;
                i += 2;
            } else if (form.get(i).equals("+")) prev = '+';
            else if (form.get(i).equals("-")) prev = '-';
        }
        ob.setUpdated(true);
        ob.setValue(val);
        return val;
    }

    void post() {
        for (int i = 0; i < lst.size(); i++) {
            obj ob = lst.get(i);
            ob.ind = i;
            if (ob.getFormula() != null) {
                ob.setChildren();
                for (int x : ob.getChildren()) {
                    obj ob2 = lst.get(x);
                    ob2.addParent(i);
                }
            } else ob.setUpdated(true);
        }
        for (obj ob : lst) {
            if (ob.isUpdated() == false) {
                getValue(ob);
            }
        }
    }

    void updater(int ind, int val, String formula) {
        // remove it as parent in every child
        for (int x : lst.get(ind).getChildren()) {
            lst.get(x).removeParent(ind);
        }
        lst.get(ind).setValue(val);
        lst.get(ind).setUpdated(false);
        lst.get(ind).setFormula(formula);
        if (lst.get(ind).getFormula() != null) {
            lst.get(ind).setChildren();
            // add it as parent in every child
            for (int x : lst.get(ind).getChildren()) {
                lst.get(x).addParent(ind);
            }
        } else {
            lst.get(ind).setChildren();
            lst.get(ind).setUpdated(true);
        }
        for (int x : lst.get(ind).getParents()) {
            obj ob2 = lst.get(x);
            updater(x, ob2.value, ob2.getFormula());
        }
    }

    void update(int ind, int val, String formula) {
        updater(ind, val, formula);
        post();
    }
}