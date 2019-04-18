from implementation.utils.file_reader import *
from implementation.x_path import parser as x_path_parser
from implementation.regular_expresions import parser as regex_parser
from implementation.road_runner import parser as road_runner

html1 = read_file('../data/overstock.com/jewelery1.html')
html2 = read_file('../data/overstock.com/jewelery2.html')

print(x_path_parser.xpath_parse_page_1(html1))

print(regex_parser.regex_parse_page_1(html1))

print(road_runner.road_runner(html1,html2))



