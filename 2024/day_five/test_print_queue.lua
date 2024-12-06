local luaunit = require("luaunit")

local print_queue = require("print_queue")

TestPrintQueue = {}

function TestPrintQueue:testHelloWorld()
	luaunit.assertEquals(print_queue.hello_world(), "Hello World!")
end

os.exit(luaunit.LuaUnit.run())
