import re
from collections import defaultdict


def fill_dictionary(res):
    for i in range(0, len(res), 36):
        if res[i + 1].isupper():
            stacks[1] = list(res[i + 1]) + stacks[1]
        if res[i + 5].isupper():
            stacks[2] = list(res[i + 5]) + stacks[2]
        if res[i + 9].isupper():
            stacks[3] = list(res[i + 9]) + stacks[3]
        if res[i + 13].isupper():
            stacks[4] = list(res[i + 13]) + stacks[4]
        if res[i + 17].isupper():
            stacks[5] = list(res[i + 17]) + stacks[5]
        if res[i + 21].isupper():
            stacks[6] = list(res[i + 21]) + stacks[6]
        if res[i + 25].isupper():
            stacks[7] = list(res[i + 25]) + stacks[7]
        if res[i + 29].isupper():
            stacks[8] = list(res[i + 29]) + stacks[8]
        if res[i + 33].isupper():
            stacks[9] = list(res[i + 33]) + stacks[9]


def task_two(numbers):
    for i in range(0, len(numbers), 3):
        number_of_letters = int(numbers[i])
        move_from = int(numbers[i + 1])
        move_to = int(numbers[i + 2])
        cur = []
        for i in range(number_of_letters):
            letter = stacks[move_from].pop()
            cur += letter
        if len(stacks[move_from]) == 0 and len(cur) == 3:
            stacks[move_to] += cur[::-1]
        else:
            stacks[move_to] += cur
    res = ""
    for i in range(1, 10):
        res += stacks[i].pop()
    return res


def task_one(numbers):
    for i in range(0, len(numbers), 3):
        number_of_letters = int(numbers[i])
        move_from = int(numbers[i + 1])
        move_to = int(numbers[i + 2])
        for i in range(number_of_letters):
            letter = stacks[move_from].pop()
            stacks[move_to].append(letter)
    res = ""
    for i in range(1, 10):
        res += stacks[i].pop()
    return res

with open('input.txt', 'r') as data:
    LETTERS = ""
    COMMANDS = ""
    stacks = defaultdict(list)
    i = 0
    for row in data:
        if i == 8:
            letters_with_row = " ".join(re.split("\[(.*?)\]", LETTERS))
            fill_dictionary(letters_with_row)
        elif i > 8:
            COMMANDS += row
        else:
            LETTERS += row
        i+=1

    numbers = re.findall("\d\d*", COMMANDS)
    # task1 = task1(numbers)
    task2 = task_two(numbers)
    print(task2)
    #answer




