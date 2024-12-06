package = "day_five"
version = "dev-1"
source = {
	url = "git+https://github.com/Wrongsides/advent-of-code.git",
}
description = {
	detailed = [[
Satisfied with their search on Ceres, the squadron of scholars suggests subsequently scanning the stationery stacks
of sub-basement 17.]],
	homepage = "https://github.com/Wrongsides/advent-of-code",
	license = "*** please specify a license ***",
}
build = {
	type = "builtin",
	modules = {
		test_print_queue = "test_print_queue.lua",
	},
}
