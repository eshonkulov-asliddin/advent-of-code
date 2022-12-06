# colories = input()
# print(colories)
contents = []
while True:
    try:
        line = input()
    except:
        break
    contents.append(line)
d = dict()
i = 0
print(contents)
cur = 0
for num in contents:
    if len(num) == 0:
        d[i] = cur
        cur = 0
        i += 1
    else:
        cur += int(num)


first_max = max(d.values())
d = {key:val for key, val in d.items() if val != first_max}
second_max = max(d.values())
d = {key:val for key, val in d.items() if val != second_max}
third_max = max(d.values())
print(first_max+second_max+third_max)

