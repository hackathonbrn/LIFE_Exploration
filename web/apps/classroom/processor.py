from apps.classroom.provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def mark_coach(self, status):
        return self.provider.mark_coach(status)

    def mark_learner(self, status):
        return self.provider.mark_learner(status)

    def take_away_activity(self, id_coach):
        return self.provider.take_away_activity(id_coach)