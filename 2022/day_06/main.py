def solve_quiz(string, len_distinct):
    for i in range(0, len(string)):
        unique = set(string[i:i+len_distinct])
        if len(unique) == len_distinct:
            return i+len_distinct

with open('input.txt', 'r') as data:
    string = ""
    for row in data:
        string += row
    task1 = solve_quiz(string, 4)
    task2 = solve_quiz(string, 14)



