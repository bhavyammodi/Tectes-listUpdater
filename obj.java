import java.util.*;

public class obj {
    int value, ind;
    String formula;

    List<String> formulaDeserialized;

    boolean updated;
    HashSet<Integer> children, parents = new HashSet<>();

    public obj(int ind, int value, boolean updated, String formula) {
        this.ind = ind;
        this.value = value;
        this.updated = formula == null ? true : updated;
        formulaDeserialized = new ArrayList<>();
        String x = "";
        if (formula != null) {
            for (int i = 0; i < formula.length(); i++)
                if (formula.charAt(i) != ' ') x += formula.charAt(i);
        } else x = null;
        this.formula = x;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getFormula() {
        return formula;
    }

    public int getInd() {
        return ind;
    }

    public List<String> getFormulaDeserialized() {
        return formulaDeserialized;
    }

    public void setFormula(String formula) {
        String x = "";
        if (formula != null) {
            for (int i = 0; i < formula.length(); i++)
                if (formula.charAt(i) != ' ') x += formula.charAt(i);
        } else x = null;
        this.formula = x;
        setFormula();
    }

    public void setFormula() { // ok tested
        formulaDeserialized = new ArrayList<>();
        if (formula == null)
            return;

        String x = "", temp = "";
        boolean isNum = false;
        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (" []()".indexOf(ch) == -1) {
                if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
                    if (temp.length() > 0) formulaDeserialized.add(temp);
                    temp = "";
                    isNum = true;
                } else if (ch <= '9' && ch >= '0') {
                    if (isNum == false) {
                        isNum = true;
                        if (temp.length() > 0 && temp.indexOf(',') == -1) formulaDeserialized.add(temp);
                        temp = "";
                    }
                } else {
                    if (isNum == true) {
                        isNum = false;
                        if (temp.length() > 0) formulaDeserialized.add(temp);
                        temp = "";
                    }
                }
                temp += ch;
                x += ch;
            }
        }
        if (temp.length() > 0)
            formulaDeserialized.add(temp);
        this.formula = x;
    }

    public HashSet<Integer> getChildren() {
        return children;
    }

    public HashSet<Integer> getParents() {
        return parents;
    }

    public void addParent(int par) {
        parents.add(par);
    }

    public void removeParent(int par) {
        parents.remove(par);
    }

    public void setChildren() {
        children = new HashSet<>();
        setFormula();
        for (int i = 0; i < formulaDeserialized.size(); i++) {
            String now = formulaDeserialized.get(i);
            char ch = now.charAt(0);
            if (ch <= '9' && ch >= '0') {
                if (i + 1 < formulaDeserialized.size()) {
                    String next = formulaDeserialized.get(i + 1);
                    char nch = next.charAt(0);
                    if (nch <= '9' && nch >= '0') // range
                    {
                        for (int x = Integer.parseInt(now); x <= Integer.parseInt(next); x++)
                            children.add(x);
                        i++;
                    } else // cell
                        children.add(Integer.parseInt(now));
                } else // cell
                    children.add(Integer.parseInt(now));
            }
        }
    }
}