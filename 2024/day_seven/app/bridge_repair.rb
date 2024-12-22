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
    parse_input
      .map { |calibration| is_valid(calibration[:target], calibration[:values]) }
      .reduce(0, :+)
  end

  def is_valid(target, values, current_value = nil, index = 0)
    return current_value if current_value == target
    return 0 if index >= values.length

    current_value ||= values[index]
    next_index = index + 1

    if next_index < values.length
      add_result = is_valid(target, values, current_value + values[next_index], next_index)
      return target if add_result == target

      multiply_result = is_valid(target, values, current_value * values[next_index], next_index)
      return target if multiply_result == target
    end
    0
  end
end
