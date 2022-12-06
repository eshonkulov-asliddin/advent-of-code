data = open('input.txt', 'r')

def turn_to_list(data):
    data_list = []
    for row in data:
        data_list.append(row)

    return data_list


data_list = turn_to_list(data)
sum_priority_items = 0
for i in range(0 ,len(data_list), 3):
    duplicate = set(data_list[i]) & set(data_list[i+1])
    duplicate = list(duplicate & set(data_list[i+2]))
    duplicate = duplicate[1] if duplicate[0] == "\n" else duplicate[0]
    if duplicate.isupper():
        sum_priority_items += ord(duplicate) - ord("A") + 27
    else:
        sum_priority_items += ord(duplicate) - ord("a") + 1
print(sum_priority_items)

