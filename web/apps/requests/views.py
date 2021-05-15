import os

from fastapi import APIRouter
from fastapi import Depends, Request, Response
from apps.requests.processor import Processor
from apps.requests.schemes import TrainingRequest

router = APIRouter()


@router.post('/add')
def add_request(req: TrainingRequest):
    processor = Processor()
    if processor.is_request_exists(req.id_learner, req.id_coach):
        return Response(status_code=409)

    req_id = processor.add_request(req.id_coach, req.id_learner, req.cost_lesson, req.count_lessons, req.id_game)

    if req_id:
        return Response(status_code=200)
    else:
        return Response(status_code=500)


@router.get('/coach/<id_coach>')
def get_by_coach(id_coach: int):
    processor = Processor()
    return processor.get_requests_by_coach(id_coach)


@router.get('/learner/<id_learner>')
def get_by_learner(id_learner: int):
    processor = Processor()
    return processor.get_requests_by_learner(id_learner)


@router.get('/move')
def move_to_learners(id_coach: int, id_learner: int):
    processor = Processor()
    return processor.move_to_learners(id_coach, id_learner)
