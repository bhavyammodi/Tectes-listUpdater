# Tectes-listUpdater
Main is showing the Usage.
This is an implementation of List Updater where we have either a value or a formula in every object.

Example of a list
<table>
<tr><th>Intial List</th><th>Final</th></tr>

<tr><td>

| Index | Value | Formula                       | 
|-------|-------|-------------------------------| 
| 0     | 0     | "cell(1) - range[2,4] + cell(5)" |
| 1     | 0     | "range[2,2]"                  | 
| 2     | 20    | null                          | 
| 3     | 10    | null                          | 
| 4     | 0     | "cell(3) - range(2,3)"        | 
| 5     | 0     | "cell(4)"                     | 
| 6     | 10    | null                          | 

</td><td>

| Value |
|--------|
| -10    |
| 20     |
| 20     |
| 10     |
| -20    |
| -20    |
| 10     |

</td></tr> </table>

```
Example of formula: "cell(9)+range[2,4]" 
Explanation: cell(x) gives the value at index 9,
             range[x,y] gives the sum of values from x to y (both inclusive)
update() can be used to update any cell.
```

Used Depth First Search Data structure to implement it (both forward and reverse).
