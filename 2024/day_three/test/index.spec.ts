import { test, expect } from 'vitest';
import { hello } from '../src/index';

test("hello defaults to World", () => {
  expect(hello()).toBe('Hello world');
});

test("hello everyone", () => {
  expect(hello('everyone!')).toBe('Hello everyone!');
});

