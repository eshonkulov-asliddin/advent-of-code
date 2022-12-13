from collections import deque
import re

inspected_items = {}
monkeys_with_properties = {}

#function that solves our task
def solve_task(monkey_number_int, starting_items_int, actual_operation, value_operation_int, devisible_by_int,
               throw_to_monkey_if_true, throw_to_monkey_if_false, monkeys_with_properties):

    queue = deque(starting_items_int)
    while queue:
        cur_num = queue.popleft()
        #remove cur element from dictionary where monkeys properties stored
        monkeys_with_properties[monkey_number_int][1].remove(cur_num)

        #count number of inspection
        inspected_items[monkey_number_int] = 1 + inspected_items.get(monkey_number_int, 0)

        if (actual_operation == "*"):
            # check if we need to multiply or add value to itself.
            if value_operation_int == 0:
                new = cur_num * cur_num
            else:
                new = cur_num * value_operation_int
        else:
            # check if we need to multiply or add value to itself
            if value_operation_int == 0:
                new = cur_num + cur_num
            else:
                new = cur_num + value_operation_int

        throwable_int = new // 3
        if (throwable_int % devisible_by_int == 0):
            monkeys_with_properties[throw_to_monkey_if_true][1].append(throwable_int)

        else:
            monkeys_with_properties[throw_to_monkey_if_false][1].append(throwable_int)


with open("input.txt", 'r') as data:
    monkey_number = ""
    starting_items = ""
    multiply_operation = ""
    add_operation = ""
    operation_value = ""
    operations = ""
    devisible_by = ""
    true_case = ""
    false_case = ""
    i = 0
    count_monkeys = 0
    for row in data:
        if i == 0:
            monkey_number = row
        elif i == 1:
            starting_items = row
        elif i == 2:
            operations = row
        elif i == 3:
            devisible_by = row
        elif i == 4:
            true_case = row
        elif i == 5:
            false_case = row

        else:
            monkey_number_int = int(re.findall("\d", monkey_number)[0])
            starting_items_int = [int(item) for item in re.findall("\d{2}", starting_items)]
            multiply_operation = re.findall("\*", operations)
            add_operation = re.findall("\+", operations)
            actual_operation = multiply_operation[0] if multiply_operation else add_operation[0]
            value_operation_int = int(re.findall("\d+", operations)[0]) if re.findall("\d+", operations) else 0
            devisible_by_int = int(re.findall("\d+", devisible_by)[0])
            throw_to_monkey_if_true = int(re.findall("\d", true_case)[0])
            throw_to_monkey_if_false = int(re.findall("\d", false_case)[0])

            #we will store all monkeys with their properties in dict
            monkeys_with_properties[count_monkeys] = [monkey_number_int, starting_items_int, actual_operation, value_operation_int, devisible_by_int, throw_to_monkey_if_true, throw_to_monkey_if_false]
            count_monkeys += 1
            i = 0
            continue

        i += 1
    monkey_number_int = int(re.findall("\d", monkey_number)[0])
    starting_items_int = [int(item) for item in re.findall("\d{2}", starting_items)]
    multiply_operation = re.findall("\*", operations)
    add_operation = re.findall("\+", operations)
    actual_operation = multiply_operation[0] if multiply_operation else add_operation[0]
    value_operation_int = int(re.findall("\d+", operations)[0]) if re.findall("\d+", operations) else 0
    devisible_by_int = int(re.findall("\d+", devisible_by)[0])
    throw_to_monkey_if_true = int(re.findall("\d", true_case)[0])
    throw_to_monkey_if_false = int(re.findall("\d", false_case)[0])

    # we will store all monkeys with their properties in dict
    monkeys_with_properties[count_monkeys] = [monkey_number_int, starting_items_int, actual_operation,
                                              value_operation_int, devisible_by_int, throw_to_monkey_if_true,
                                              throw_to_monkey_if_false]

#we have 20 rounds
for round in range(20):
    for key, val in monkeys_with_properties.items():
        solve_task(val[0], val[1], val[2], val[3], val[4], val[5], val[6], monkeys_with_properties)
print(inspected_items)









