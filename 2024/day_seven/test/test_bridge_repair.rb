require 'minitest/autorun'
require_relative '../app/bridge_repair.rb'

class BridgeRepairTest < Minitest::Test
  def test_hello_world
    assert_equal "Hello, World!", hello_world
  end

  def test_create_bridge_repair_from_calibrations
    bridge = BridgeRepair.new("190: 10 19
                               3267: 81 40 27
                               83: 17 5
                               156: 15 6
                               7290: 6 8 6 15
                               161011: 16 10 13
                               192: 17 8 14
                               21037: 9 7 18 13
                               292: 11 6 16 20")
    assert bridge != nil
  end

  def test_valid_calibration
    bridge = BridgeRepair.new("3267: 81 40 27")

    result = bridge.sum_calibrations

    assert_equal 3267, result
  end

  def test_multiple_valid_calibrations
    bridge = BridgeRepair.new("190: 10 19
                               3267: 81 40 27")

    result = bridge.sum_calibrations

    assert_equal 3457, result
  end

  def test_invalid_calibration_is_not_counted
    bridge = BridgeRepair.new("190: 10 19
                               83: 17 5")

    result = bridge.sum_calibrations

    assert_equal 190, result
  end

  def test_valid_calibrations_are_summed
    bridge = BridgeRepair.new("190: 10 19
                               3267: 81 40 27
                               83: 17 5
                               156: 15 6
                               7290: 6 8 6 15
                               161011: 16 10 13
                               192: 17 8 14
                               21037: 9 7 18 13
                               292: 11 6 16 20")

    result = bridge.sum_calibrations

    assert_equal 3749, result
  end

  def test_valid_calibrations_from_file
    bridge = BridgeRepair.new(File.read("app/input"))

    result = bridge.sum_calibrations

    assert_equal 1582598718861, result
  end
end
