from builtins import str
import sys
from pathlib import Path
import os
from enum import Enum


class TreeType(Enum):
    SA = '.sa'
    SAOUT = '.saout'
    TS = '.ts'
    C3A = '.c3a'
    C3AOUT = '.c3aout'
    PRENASM = '.pre-nasm'
    FG = '.fg'
    FGSOL = '.fgs'
    NASM = '.nasm'
    OUT = '.out'


class CompareTree:
    treeType: TreeType
    input_directory: Path
    ref_directory: Path

    def __init__(self, given_type, input_directory, dict_directories):
        self.treeType = TreeType(given_type)

        if self.treeType is not None:
            self.input_directory = input_directory
            self.ref_directory = dict_directories[self.treeType]

    def ignoreComments(self, input_line):
        if self.treeType is TreeType.C3A:
            return input_line.split('#')[0]
        if self.treeType is TreeType.PRENASM:
            return input_line.split(';')[0]
        if self.treeType is TreeType.FG:
            return input_line.split(';')[0]
        if self.treeType is TreeType.FGSOL:
            return input_line.split(';')[0]
        if self.treeType is TreeType.NASM:
            return input_line.split(';')[0]
        else:
            return input_line

    def compareFile(self, filename, print_file):
        file_path = Path(self.input_directory).joinpath(filename)
        compare_path = Path(self.ref_directory).joinpath(filename)
        if not file_path.suffix.endswith(self.treeType.value):
            exit()
        # try:
        print_file.write("#############\t" + filename + "\t###################################\n")
        input_file = open(file_path, 'r')
        ref_file = open(compare_path, 'r')
        nbError = 0
        for line in input_file:
            input_line_formatted = self.ignoreComments(line)
            ref_line = ref_file.readline()
            ref_line_formatted = self.ignoreComments(ref_line)
            if input_line_formatted != ref_line_formatted:
                nbError = nbError + 1
                print_file.write("\tINPUT: " + line + "\tREF: " + ref_line + "\n")
        print_file.write("nb error in file: " + str(nbError) + "\n")
        print_file.write("------------------------------------------------------------------------------------------------------------\n\n\n")
        return nbError
        # except Exception as e:
            # print(e, file=sys.stderr)


def main():
    root = os.path.dirname(os.path.abspath(__file__))
    input_directory = Path(root).joinpath('input')
    dict_directories = {}
    dict_directories[TreeType.SA] = Path(root).joinpath('sa-ref')
    dict_directories[TreeType.SAOUT] = Path(root).joinpath('saout-ref')
    dict_directories[TreeType.TS] = Path(root).joinpath('ts-ref')
    dict_directories[TreeType.C3A] = Path(root).joinpath('c3a-ref')
    dict_directories[TreeType.C3AOUT] = Path(root).joinpath('c3aout-ref')
    dict_directories[TreeType.PRENASM] = Path(root).joinpath('prenasm-ref')
    dict_directories[TreeType.FG] = Path(root).joinpath('fg-ref')
    dict_directories[TreeType.FGSOL] = Path(root).joinpath('fgs-ref')
    dict_directories[TreeType.NASM] = Path(root).joinpath('nasm-ref')
    dict_directories[TreeType.OUT] = Path(root).joinpath('out-ref')
    dict_instances = {}
    dict_instances[TreeType.SA] = CompareTree(given_type='.sa', input_directory=input_directory, dict_directories=dict_directories)
    dict_instances[TreeType.TS] = CompareTree(given_type='.ts', input_directory=input_directory,
                                              dict_directories=dict_directories)
    dict_instances[TreeType.SAOUT] = CompareTree(given_type='.saout', input_directory=input_directory,
                                              dict_directories=dict_directories)
    dict_instances[TreeType.C3A] = CompareTree(given_type='.c3a', input_directory=input_directory,
                                              dict_directories=dict_directories)
    dict_instances[TreeType.C3AOUT] = CompareTree(given_type='.c3aout', input_directory=input_directory,
                                              dict_directories=dict_directories)
    dict_instances[TreeType.PRENASM] = CompareTree(given_type='.pre-nasm', input_directory=input_directory,
                                              dict_directories=dict_directories)
    dict_instances[TreeType.FG] = CompareTree(given_type='.fg', input_directory=input_directory,
                                                   dict_directories=dict_directories)
    dict_instances[TreeType.FGSOL] = CompareTree(given_type='.fgs', input_directory=input_directory,
                                                   dict_directories=dict_directories)
    dict_instances[TreeType.NASM] = CompareTree(given_type='.nasm', input_directory=input_directory,
                                                   dict_directories=dict_directories)
    dict_instances[TreeType.OUT] = CompareTree(given_type='.out', input_directory=input_directory,
                                                   dict_directories=dict_directories)
    file_results = open(Path(root).joinpath('compare_results.txt'), 'w')
    nb_errors = 0

    for filename in os.listdir(input_directory):
        # Path to the file
        file_path = Path(input_directory).joinpath(filename)
        try:
            treeType = TreeType(file_path.suffix)
        except ValueError:
            continue
        nb_errors += dict_instances[treeType].compareFile(filename=filename, print_file=file_results)

    file_results.write('\n\n#######################################################################################\n')
    file_results.write('############################# RESULTS #################################################\n')
    file_results.write('===>\tTotal errors found during comparison: ' + str(nb_errors) + '\n')
    file_results.write('#######################################################################################\n')


if __name__ == '__main__':
    main()
