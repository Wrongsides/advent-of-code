def hello_world
  "Hello, World!"
end

class BridgeRepair
  def initialize(input)
    @input = input
  end

  def parse_input
    @input.lines.map do |line|
      target, values = line.strip.split(":").map(&:strip)
      { target: target.to_i, values: values.split.map(&:to_i) }
    end
  end

  def sum_calibrations
    operations ||= [
      ->(a, b) { a + b },
      ->(a, b) { a * b }
    ]
    sum(operations)
  end

  def sum_calibrations_with_concatonation
    operations ||= [
      ->(a, b) { a + b },
      ->(a, b) { a * b },
      ->(a, b) { "#{a}#{b}".to_i}
    ]
    sum(operations)
  end

  def sum(operations)
    parse_input
      .map { |calibration| is_valid(operations, calibration[:target], calibration[:values]) }
      .reduce(0, :+)
  end

  def is_valid(operations, target, values)
    return 0 if values.length < 2
    return target if try_operations(operations, target, values[0], values[1..])
    0
  end

  def try_operations(operations, target, current_value, remaining_values)
    return current_value == target if remaining_values.empty?

    operations.each do |operation|
      result = operation.call(current_value, remaining_values[0])
      return true if try_operations(operations, target, result, remaining_values[1..])
    end

    false
  end
end
