from apps.requests.provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def add_request(self, id_coach, id_learner, cost_lesson, count_lessons, id_game):
        return self.provider.add_request(id_coach, id_learner, cost_lesson, count_lessons, id_game)

    def is_request_exists(self, id_learner, id_coach):
        return self.provider.is_requests_exists(id_learner, id_coach)

    def get_requests_by_coach(self, id_coach):
        return self.provider.get_requests_by_coach(id_coach)

    def get_requests_by_learner(self, id_learner):
        return self.provider.get_requests_by_learner(id_learner)

    def move_to_learners(self, id_coach, id_learner):
        return self.provider.move_to_learners(id_coach, id_learner)