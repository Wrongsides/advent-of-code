local luaunit = require("luaunit")

local print_queue = require("print_queue")

TestPrintQueue = {}

function TestPrintQueue:test_hello_world()
	luaunit.assertEquals(print_queue.hello_world(), "Hello World!")
end

function TestPrintQueue:test_left_before_right_in_update_returns_middle_page()
	local rules = { "47|53" }
	local updates = { "75,47,61,53,29" }
	luaunit.assertEquals(print_queue.apply(rules, updates), 61)
end

function TestPrintQueue:test_right_before_left_in_update_returns_0()
	local rules = { "97|75" }
	local updates = { "75,97,47,61,53" }
	luaunit.assertEquals(print_queue.apply(rules, updates), 0)
end

function TestPrintQueue:test_all_rules_against_first_update()
	local rules = TestPrintQueue.example_rules(self)
	local updates = { "75,47,61,53,29" }
	luaunit.assertEquals(print_queue.apply(rules, updates), 61)
end

function TestPrintQueue:test_all_rules_against_bad_update()
	local rules = TestPrintQueue.example_rules(self)
	local updates = { "75,97,47,61,53" }
	luaunit.assertEquals(print_queue.apply(rules, updates), 0)
end

function TestPrintQueue:test_sum_middle_page_of_all_valid_updates()
	local rules = TestPrintQueue.example_rules(self)
	local updates = TestPrintQueue.example_updates(self)
	luaunit.assertEquals(print_queue.apply(rules, updates), 143)
end

function TestPrintQueue:test_file_input()
	local rules = print_queue.read_rules("input")
	local updates = print_queue.read_updates("input")
	luaunit.assertEquals(print_queue.apply(rules, updates), 5948)
end

function TestPrintQueue:example_rules()
	return {
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
end

function TestPrintQueue:example_updates()
	return {
		"75,47,61,53,29",
		"97,61,53,29,13",
		"75,29,13",
		"75,97,47,61,53",
		"61,13,29",
		"97,13,75,29,47",
	}
end

os.exit(luaunit.LuaUnit.run())
