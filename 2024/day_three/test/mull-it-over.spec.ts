import { test, expect } from 'vitest';
import { Program } from '../src/mull-it-over';

test("multiply numbers", () => {
  expect(new Program().multiply(2, 2)).toBe(4);
});

test("extract instruction", () => {
  expect(new Program().parse('mul(44,46)')).toStrictEqual([44, 46]);
});

test("processes valid input", () => {
  expect(new Program().run('mul(44,46)')).toBe(2024);
});

test("processes three digit input", () => {
  expect(new Program().run('mul(123,4)')).toBe(492);
});

test("clean invalid characters from instructions", () => {
  const input = 'xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))';

  const instructions: string[] = new Program().clean(input);

  expect(instructions).toStrictEqual(['mul(2,4)', 'mul(5,5)', 'mul(11,8)', 'mul(8,5)']);
});

test("can sum the currupted instructions from file", () => {

  const total: number = new Program().runFile("src/input");

  expect(total).toBe(184122457);
});

test("can handle do and dont commands", () => {
  const input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

  const total: number = new Program().runWithCommands(input);

  expect(total).toBe(48);
});

test("can handle trailing dont commands", () => {
  const input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5)don't()mul(2,2))"

  const total: number = new Program().runWithCommands(input);

  expect(total).toBe(48);
});

test("can sum the currupted instructions from file with commands", () => {

  const total: number = new Program().runFileWithCommands("src/input");

  expect(total).toBe(107862689);
});

