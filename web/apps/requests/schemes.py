from pydantic import BaseModel


class TrainingRequest(BaseModel):
    id_coach: int
    id_learner: int
    cost_lesson: int
    count_lessons: int
    id_game: int