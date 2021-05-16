from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('requests')

    def add_request(self, id_coach, id_learner, cost_lesson, count_lessons, id_game):
        return self.exec_by_file('add_request.tmpl', {
            'id_coach': id_coach,
            'id_learner': id_learner,
            'cost_lesson': cost_lesson,
            'count_lessons': count_lessons,
            'id_game': id_game
        })
    #[0].get('id_request')

    def move_to_learners(self, id_coach, id_learner):
        return self.exec_by_file('send_to_learners.tmpl', {
            'id_coach': id_coach,
            'id_learner': id_learner
        })

    def is_requests_exists(self, id_learner, id_coach):
        answer = self.exec_by_file('is_requests_exists.tmpl', {
            'id_learner': id_learner,
            'id_coach': id_coach
        })
        return answer[0].get('count') if answer else None

    def get_requests_by_coach(self, id_coach):
        answer = self.exec_by_file('get_requests_by_coach.tmpl', {
            'id_coach': id_coach
        })
        return answer[0] if answer else None

    def get_requests_by_learner(self, id_learner):
        answer = self.exec_by_file('get_requests_by_learner.tmpl', {
            'id_learner': id_learner
        })
        return answer[0] if answer else None