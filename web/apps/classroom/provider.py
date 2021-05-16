from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('classroom')

    def mark_coach(self, status):
        return self.exec_by_file('mark_coach.tmpl', {'status': status})

    def mark_learner(self, status):
        return self.exec_by_file('mark_learner.tmpl', {'status': status})

    def take_away_activity(self, id_coach):
        return self.exec_by_file('take_away_activity.tmpl', {'id_coach': id_coach})
