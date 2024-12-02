from day_one import historian_hysteria


def test_reconsile_the_distance_between_the_same_location_is_zero():
    assert 0 == historian_hysteria.reconsile([1], [1])

def test_the_distance_is_between_the_two_smallest_location_ids():
    list_one = [1, 5]
    list_two = [5, 2]
    assert 1 == historian_hysteria.reconsile(list_one, list_two)

def test_the_distance_of_multiple_location_ids_are_added_together():
    list_one = [3, 4, 2, 1, 3, 3]
    list_two = [4, 3, 5, 3, 9, 3]
    assert 11 == historian_hysteria.reconsile(list_one, list_two)

def test_reconsile_lists_from_a_file():
    assert 765748 == historian_hysteria.reconsileFile("day_one/input")

def test_similarity_of_locations_is_id_multiplied_by_occurrences():
    list_one = [2, 3, 4]
    list_two = [2, 2, 5]
    assert 4 == historian_hysteria.similarity(list_one, list_two)

def test_the_similarity_of_multiple_location_ids_are_added_together():
    list_one = [3, 4, 2, 1, 3, 3]
    list_two = [4, 3, 5, 3, 9, 3]
    assert 31 == historian_hysteria.similarity(list_one, list_two)

def test_similarity_of_lists_from_a_file():
    assert 27732508 == historian_hysteria.similarityFile("day_one/input")

