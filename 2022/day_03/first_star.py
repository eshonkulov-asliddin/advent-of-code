data = open('input.txt', 'r')
sum_priority_items = 0
for row in data:
    mid = len(row)//2
    s1 = row[:mid]
    s2 = row[mid:]
    duplicate = list(set(s1) & set(s2))[0]
    if duplicate.isupper():
        sum_priority_items += ord(duplicate) - ord("A") + 27
    else:
        sum_priority_items += ord(duplicate) - ord("a") + 1
print(sum_priority_items)
