import numpy as np

cell_list = {
    "add": [8, 16, 32, 64],
    "sub": [8, 16, 32, 64],
    "mul": [8, 16, 32, 64],
    "mux": [0, 0],
    "not": [0, 0],
    "and": [0, 0], 
    "or": [0, 0],
    "xor": [0, 0],
    "shl": [4, 8, 16, 32, 64],
    "shr": [4, 8, 16, 32, 64],
    "reduce_and": [4, 8, 16, 32, 64],
    "reduce_or": [4, 8, 16, 32, 64],
    "reduce_xor": [4, 8, 16, 32, 64],
    "eq": [8, 16, 32, 64],
    "ne": [8, 16, 32, 64],
    "le": [8, 16, 32, 64],
    "lt": [8, 16, 32, 64],
    "ge": [8, 16, 32, 64],
    "gt": [8, 16, 32, 64],
    "div": [8, 16, 32, 64],
    "mod": [8, 16, 32, 64]
}

freq_list = {
    "add": 563907,
    "sub": 367767,
    "mul": 139779,
    "mux": 23179756,
    "not": 1397792 + 68862,
    "and": 8523294, 
    "or": 2336064,
    "xor": 473157,
    "shl": 236952,
    "shr": 49638 + 239364,
    "reduce_and": 32668,
    "reduce_or": 1663244,
    "reduce_xor": 40,
    "eq": 4337592,
    "ne": 41631,
    "le": 99747,
    "lt": 239364,
    "ge": 98322,
    "gt": 15537,
    "div": 57,
    "mod": 30
}

def random_sample(bus_width, freq_list, cell_list):
    keys = list(freq_list.keys())
    freq = []
    for k in keys:
        freq.append(freq_list[k])
    freq = np.sqrt(np.array(freq))
    p = freq / np.sum(freq)
    a = np.array(keys)
    choice = np.random.choice(a, 1, p=p)[0]
    
    cell_widths = cell_list[choice]
    width_choice = 0
    if 0 not in cell_widths:
        width_choice = np.random.choice(cell_widths)
    else:
        width_choice = bus_width

    ret = str(choice) + "," + str(width_choice) + "," + str(width_choice)
    return ret


def generate_sequence(seq_len, bus_width, freq_list, cell_list):
    ret = ""
    for i in range(seq_len):
        ret += random_sample(bus_width, freq_list, cell_list)
        ret += "\n"
    return ret


if __name__ == "__main__":
    mu, sigma = 80, 3
    s = int(abs(np.random.normal(mu, sigma, 1)[0]))
    ret = generate_sequence(s, 64, freq_list, cell_list)
    outfile = open("sequence.txt", "w+")
    outfile.write(ret)
    outfile.close()
