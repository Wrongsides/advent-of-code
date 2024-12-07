local print_queue = {}

function print_queue.hello_world()
	return "Hello World!"
end

local function split(update, delimiter)
	local update_table = {}
	for match in update:gmatch("([^" .. delimiter .. "]+)") do
		table.insert(update_table, match)
	end
	return update_table
end

local function get_middle_page(update)
	local middle_index = math.floor(#update / 2)
	return update[middle_index + 1]
end

local function valid_update(rule, update)
	local left, right = rule:match("([^|]+)|([^|]+)")
	local left_index, right_index = nil, nil

	for i, page in ipairs(update) do
		if tonumber(page) == tonumber(left) then
			left_index = i
		end
		if tonumber(page) == tonumber(right) then
			right_index = i
		end
	end

	if left_index and right_index and left_index > right_index then
		return false
	end
	return true
end

local function check_updates(rules, update)
	for _, rule in ipairs(rules) do
		if not valid_update(rule, update) then
			return 0
		end
	end
	return tonumber(get_middle_page(update))
end

function print_queue.apply(rules, updates)
	local total = 0
	for _, update in ipairs(updates) do
		local update_table = type(update) == "string" and split(update, ",") or update
		local result = check_updates(rules, update_table)
		total = total + result
	end
	return total
end

function print_queue.read_rules(filename)
	local file = io.open(filename, "r")
	if not file then
		print("File not found")
		return
	end
	local rules = {}
	for rule in file:lines() do
		table.insert(rules, rule)
		if rule == "" then
			break
		end
	end
	return rules
end

function print_queue.read_updates(filename)
	local file = io.open(filename, "r")
	if not file then
		print("File not found")
		return
	end
	local skipped_rules = false
	local updates = {}
	for update in file:lines() do
		if skipped_rules then
			table.insert(updates, update)
		elseif update == "" then
			skipped_rules = true
		end
	end
	return updates
end

return print_queue
