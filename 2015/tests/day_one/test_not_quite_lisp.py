
from day_one import not_quite_lisp


def test_santa_starts_at_floor_0():
    assert 0 == not_quite_lisp.moveSanta("")

def test_open_parenthesis_means_santa_goes_up_one_floor():
    floor = not_quite_lisp.moveSanta("(")
    assert 1 == floor

def test_close_parenthesis_means_santa_goes_down_one_floor():
    floor = not_quite_lisp.moveSanta(")")
    assert -1 == floor

def test_three_different_routes_to_the_third_floor():
    route_one = "((("
    route_two = "(()(()("
    route_three = "))((((("
    assert 3 == not_quite_lisp.moveSanta(route_one)
    assert 3 == not_quite_lisp.moveSanta(route_two)
    assert 3 == not_quite_lisp.moveSanta(route_three)

def test_two_routes_to_the_basement():
    route_one = "())"
    route_two = "))("
    assert -1 == not_quite_lisp.moveSanta(route_one)
    assert -1 == not_quite_lisp.moveSanta(route_two)

def test_two_routes_to_the_third_basement():
    route_one = ")))"
    route_two = ")())())"
    assert -3 == not_quite_lisp.moveSanta(route_one)
    assert -3 == not_quite_lisp.moveSanta(route_two)

def test_santa_can_follow_the_route_from_a_file():
    assert 280 == not_quite_lisp.moveSanta("day_one/input")

def test_position_santa_enters_the_basement():
    assert 1 == not_quite_lisp.entersBasementAt(")")
    assert 5 == not_quite_lisp.entersBasementAt("()())")

def test_santa_enters_basement_from_file_route_at_position():
    assert 1797 == not_quite_lisp.entersBasementAt("day_one/input")

