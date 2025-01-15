#!/usr/bin/env sh

if ! command -v gh >/dev/null 2>&1; then
    echo "GitHub CLI가 설치되어 있지 않습니다. https://cli.github.com/ 에서 설치해주세요."
    exit 1
fi

if ! gh auth status >/dev/null 2>&1; then
    echo "GitHub CLI 인증이 필요합니다. 'gh auth login' 명령어로 인증을 진행해주세요."
    exit 1
fi

date_fmt="$(date +%Y-%m-%d)"
name='손세림'
merge_to="student/05-$name"
title="[$date_fmt] $name - 과제 제출"

if ! gh pr create --base "$merge_to" \
    --title "$title" \
    --editor "$@"; then
    echo "PR 생성 오류" >&2
    exit 2
fi
