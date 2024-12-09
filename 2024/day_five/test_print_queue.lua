local luaunit = require("luaunit")

local print_queue = require("print_queue")

TestPrintQueue = {}

local example_rules = {
	"47|53",
	"97|13",
	"97|61",
	"97|47",
	"75|29",
	"61|13",
	"75|53",
	"29|13",
	"97|29",
	"53|29",
	"61|53",
	"97|53",
	"61|29",
	"47|13",
	"75|47",
	"97|75",
	"47|61",
	"75|61",
	"47|29",
	"75|13",
	"53|13",
}

local example_updates = {
	"75,47,61,53,29",
	"97,61,53,29,13",
	"75,29,13",
	"75,97,47,61,53",
	"61,13,29",
	"97,13,75,29,47",
}

function TestPrintQueue:test_hello_world()
	luaunit.assertEquals(print_queue.hello_world(), "Hello World!")
end

function TestPrintQueue:test_left_before_right_in_update_returns_middle_page()
	local input = { ["rules"] = { "47|53" }, ["updates"] = { "75,47,61,53,29" } }
	luaunit.assertEquals(print_queue.check_valid(input), 61)
end

function TestPrintQueue:test_right_before_left_in_update_returns_0()
	local input = { ["rules"] = { "97|75" }, ["updates"] = { "75,97,47,61,53" } }
	luaunit.assertEquals(print_queue.check_valid(input), 0)
end

function TestPrintQueue:test_all_rules_against_first_update()
	local input = { ["rules"] = example_rules, ["updates"] = { "75,47,61,53,29" } }
	luaunit.assertEquals(print_queue.check_valid(input), 61)
end

function TestPrintQueue:test_all_rules_against_bad_update()
	local input = { ["rules"] = example_rules, ["updates"] = { "75,97,47,61,53" } }
	luaunit.assertEquals(print_queue.check_valid(input), 0)
end

function TestPrintQueue:test_sum_middle_page_of_all_valid_updates()
	local input = { ["rules"] = example_rules, ["updates"] = example_updates }
	luaunit.assertEquals(print_queue.check_valid(input), 143)
end

function TestPrintQueue:test_file_input()
	local input = print_queue.read_file("input")
	luaunit.assertEquals(print_queue.check_valid(input), 5948)
end

function TestPrintQueue:test_sort_invalid_update()
	local input = { ["rules"] = example_rules, ["updates"] = { "75,97,47,61,53" } }
	luaunit.assertEquals(print_queue.check_invalid(input), 47)
end

function TestPrintQueue:test_sum_middle_page_of_all_invalid_updates()
	local input = { ["rules"] = example_rules, ["updates"] = example_updates }
	luaunit.assertEquals(print_queue.check_invalid(input), 123)
end

function TestPrintQueue:test_file_input_for_invalids()
	local input = print_queue.read_file("input")
	luaunit.assertEquals(print_queue.check_invalid(input), 3062)
end

os.exit(luaunit.LuaUnit.run())
