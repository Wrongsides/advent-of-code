local print_queue = {}

function print_queue.hello_world()
	return "Hello World!"
end

local function split(str, delimiter)
	local tokens = {}
	for value in str:gmatch("([^" .. delimiter .. "]+)") do
		table.insert(tokens, tonumber(value))
	end
	return tokens
end

function print_queue.read_file(filename)
	local file = io.open(filename, "r")
	if not file then
		print("File not found")
		return
	end
	local first_half = true
	local rules = {}
	local updates = {}
	for line in file:lines() do
		if line == "" then
			first_half = false
		elseif first_half then
			table.insert(rules, line)
		else
			table.insert(updates, line)
		end
	end
	return { ["rules"] = rules, ["updates"] = updates }
end

local function get_middle_page(update)
	return update[math.floor(#update / 2) + 1]
end

local function parse_rules(input)
	local rules = {}
	for _, rule in ipairs(input.rules) do
		table.insert(rules, split(rule, "|"))
	end
	return rules
end

local function valid_update(rules, update)
	for _, rule in ipairs(rules) do
		local left_index, right_index = nil, nil
		for index, page in ipairs(update) do
			if page == rule[1] then
				left_index = index
			end
			if page == rule[2] then
				right_index = index
			end
		end

		if left_index and right_index and left_index > right_index then
			return false
		end
	end
	return true
end

function print_queue.check_valid(input)
	local rules = parse_rules(input)
	local total = 0
	for _, update in ipairs(input.updates) do
		update = split(update, ",")
		if valid_update(rules, update) then
			total = total + get_middle_page(update)
		end
	end
	return total
end

local function create_rule_map(rules)
	local rule_map = {}
	for _, rule in ipairs(rules) do
		local left, right = rule[1], rule[2]
		if not rule_map[left] then
			rule_map[left] = { before = {} }
		end
		if not rule_map[right] then
			rule_map[right] = { before = {} }
		end

		rule_map[left].before[right] = true
	end
	return rule_map
end

local function swap(update, i, j)
	update[i], update[j] = update[j], update[i]
end

local function sort(rules_map, update)
	for current_page = 1, #update do
		local is_before_current = rules_map[update[current_page]].before
		for next_page = current_page + 1, #update do
			if is_before_current[update[next_page]] then
				swap(update, current_page, next_page)
				return sort(rules_map, update)
			end
		end
	end
end

function print_queue.check_invalid(input)
	local rules = parse_rules(input)
	local rules_map = create_rule_map(rules)
	local total = 0
	for _, update in ipairs(input.updates) do
		update = split(update, ",")
		if not valid_update(rules, update) then
			sort(rules_map, update)
			total = total + get_middle_page(update)
		end
	end
	return total
end

return print_queue
