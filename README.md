![SkipList](https://github.com/BinaryzzOrg/SkipList/assets/54339540/13b59b27-5531-49cb-8e77-f6b6da8e0eb5)

## UnionFind - a data structure that allows for efficient search, insertion and deletion of elements in a sorted list.

# Instructions
###### Case Problem:
Modify the skip list sample program and add the following operations:

1. Search - Searching in a skip list is similar to searching in a linked list. You start from the top level and
traverse nodes horizontally until you find a node with a value greater than or equal to the target value.
Then, you drop down to the next level and continue the search. If the target value is found, return the
node; otherwise, return null.

2. Range Queries - Skip lists can efficiently support range queries, where you want to retrieve all
elements within a specified range. You can start at the top level and traverse horizontally until you find
the first node with a value greater than or equal to the lower bound of the range. Then, you drop down
to lower levels and continue the search until you find a node with a value greater than the upper bound
of the range or reach the end of the list.
###### Sample Output:
![image](https://github.com/BinaryzzOrg/SkipList/assets/54339540/dfea6939-1675-48f8-8236-aa9e215d8369)

