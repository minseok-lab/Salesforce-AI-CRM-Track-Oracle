-- Den보다 급여를 많이 받는 사람의 이름과 급여는?

SELECT
	e.EMPLOYEE_ID ,
	e.FIRST_NAME ,
	e.SALARY 
FROM EMPLOYEES e 
WHERE e.SALARY > (
	SELECT e2.SALARY 
	FROM EMPLOYEES e2 
	WHERE e2.FIRST_NAME = 'Den');


-- 급여를 가장 적게 받는 사람의 이름, 급여, 사원번호는?

SELECT
	e.FIRST_NAME ,
	e.SALARY ,
	e.EMPLOYEE_ID 
FROM EMPLOYEES e 
WHERE e.SALARY = (
	SELECT  MIN(e2.SALARY )
	FROM EMPLOYEES e2 );


-- 평균 급여보다 적게 받는 사람의 이름, 급여를 출력하세요

SELECT 
	e.FIRST_NAME ,
	e.SALARY 
FROM EMPLOYEES e 
WHERE e.SALARY < (
	SELECT AVG(e2.SALARY )
	FROM EMPLOYEES e2 );


-- 
SELECT e.FIRST_NAME , e.SALARY 
FROM EMPLOYEES e 
WHERE e.SALARY IN (
	SELECT e2.SALARY 
	FROM EMPLOYEES e2 
	WHERE e2.DEPARTMENT_ID = 110);

--
-- 각 부서별로 최고급여를 받는 사원을 출력하세요.

SELECT 
	e.DEPARTMENT_ID AS 부서ID,
	e.EMPLOYEE_ID AS 사원번호,
	e.FIRST_NAME AS 이름,
	e.SALARY AS 급여 
FROM EMPLOYEES e 
WHERE (e.DEPARTMENT_ID, e.SALARY)
IN (SELECT
		e2.DEPARTMENT_ID ,
		MAX(e2.SALARY )
	FROM EMPLOYEES e2 
	GROUP BY e2.DEPARTMENT_ID) ;


--
-- 각 부서별로 최고급여를 받는 사원을 출력하세요 - 테이블에서 조인

SELECT 
	e.DEPARTMENT_ID , 
	e.EMPLOYEE_ID ,
	e.FIRST_NAME ,
	e.SALARY 
FROM EMPLOYEES e, 
	(SELECT
		e2.DEPARTMENT_ID ,
		MAX(e2.SALARY ),
		e2.SALARY 
	FROM EMPLOYEES e2  
	GROUP BY e2.DEPARTMENT_ID, e2.SALARY  ) s
WHERE e.DEPARTMENT_ID = s.DEPARTMENT_ID 
AND e.SALARY = s.SALARY ;


--
-- RowNum 예제1
SELECT
	rownum,
	e.FIRST_NAME ,
	e.SALARY 
FROM EMPLOYEES e 
ORDER BY 3 DESC;


--
-- RowNum 예제2
SELECT
	rownum,
	e2.FIRST_NAME ,
	e2.SALARY 
FROM (
	SELECT e.FIRST_NAME , e.SALARY 
	FROM EMPLOYEES e 
	ORDER BY e.SALARY DESC) e2
WHERE rownum <= 3;


--
-- rownum 예제3

SELECT
	rn,
	e2.FIRST_NAME ,
	e2.SALARY 
FROM (
	SELECT 
		rownum AS rn,
		e1.FIRST_NAME ,
		e1.SALARY 
	FROM (
		SELECT
			e.FIRST_NAME ,
			e.SALARY 
		FROM EMPLOYEES e
		ORDER BY e.SALARY DESC) e1
	 ) e2
WHERE rn >= 11
AND rn <=20;


--
-- rownum 예제4
-- 07년에 입사한 직원 중 급여가 많은 직원 중 3에서 7등의 이름, 급여, 입사일은?

SELECT
	e2.rn AS 등수,
	e2.FIRST_NAME AS 이름,
	e2.SALARY AS 급여,
	TO_CHAR(e2.HIRE_DATE, 'YYYY-MM-DD') AS 입사일
FROM (
	SELECT 
		rownum AS rn,
		e1.FIRST_NAME ,
		e1.SALARY ,
		e1.HIRE_DATE 
	FROM (
		SELECT 
			e.FIRST_NAME ,
			e.SALARY ,
			e.HIRE_DATE 
		FROM EMPLOYEES e 
		WHERE TO_CHAR(e.HIRE_DATE, 'YYYY') = 2007
		ORDER BY e.SALARY DESC ) e1
	) e2
WHERE e2.rn >= 3
AND e2.rn <= 7;


-- 연습문제1
-- 평균 금액보다 적은 급여를 받는 직원은 몇 명이나 있습니까? (56명)

SELECT 
	COUNT(e.EMPLOYEE_ID ) AS "직원 수"
FROM EMPLOYEES e 
WHERE e.SALARY < (
	SELECT AVG(e2.SALARY )
	FROM EMPLOYEES e2);


-- 연습문제2
-- 각 부서별로 최고의 급여를 받는 사원의 직원번호 (employee_id), 성(last_name)과 급여(salary), 부서번호(department_id)를 조회하세요.
-- 단, 조회결과는 급여의 내림차순으로 정렬되어 나타

SELECT 
FROM (
	SELECT
		e.EMPLOYEE_ID ,
		e.LAST_NAME ,
		e.SALARY ,
		e.DEPARTMENT_ID 
	FROM EMPLOYEES e );


-- 연습문제3
-- 각 업무(job) 별로 급여(salary)의 총합을 구하고자 합니다.
-- 급여 총합이 가장 높은 업무부터 업무명(job_title)과 급여 총합을 조회하시오

SELECT
	j.JOB_TITLE AS 업무명,
	e.TOTAL AS 급여
FROM (
	SELECT
		SUM(e1.SALARY ) AS total,
		e1.JOB_ID 
	FROM EMPLOYEES e1
	GROUP BY e1.JOB_ID
	) e
JOIN JOBS j 
ON e.JOB_ID = j.JOB_ID 
ORDER BY e.TOTAL DESC ;


-- 연습문제1.
-- 가장 늦게 입사한 직원의 이름과 급여와 근무하는 부서 이름은?
SELECT 
	e.FIRST_NAME || ' ' || e.LAST_NAME AS 이름,
	e.SALARY AS 급여,
	d.DEPARTMENT_NAME AS 부서명,
	TO_CHAR(e.HIRE_DATE ,'YYYY-MM-DD') AS 입사일
FROM (
	SELECT
		rownum AS rowNums,
		e1.FIRST_NAME ,
		e1.LAST_NAME ,
		e1.SALARY ,
		e1.HIRE_DATE ,
		e1.DEPARTMENT_ID 
	FROM (
		SELECT 
			e2.FIRST_NAME ,
			e2.LAST_NAME ,
			e2.SALARY ,
			e2.HIRE_DATE ,
			e2.DEPARTMENT_ID 
		FROM EMPLOYEES e2 
		ORDER BY e2.HIRE_DATE DESC ) e1
	) e
JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE e.rowNums <= 2;


-- 연습문제3
-- 평균 급여가 가장 높은 부서명은?
SELECT 
	d.DEPARTMENT_NAME AS 부서명
FROM
	(
	SELECT
		e1.DEPARTMENT_ID ,
		rownum AS rn
	FROM
		(
		SELECT
			e2.DEPARTMENT_ID ,
			AVG(e2.SALARY) AS avgSalary
		FROM
			EMPLOYEES e2
		GROUP BY
			e2.DEPARTMENT_ID
		ORDER BY
			2 DESC 
		) e1
	) e
JOIN DEPARTMENTS d 
ON
	e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE
	e.RN = 1;


---
-- 숙제. 평균 급여가 가장 높은 지역(대륙)은?

SELECT
	gg."지역명"
FROM
	(
	SELECT
		AVG(e.SALARY) AS "평균 급여",
		r.REGION_NAME AS "지역명"
	FROM
		EMPLOYEES e
	JOIN DEPARTMENTS d 
	ON
		e.DEPARTMENT_ID = d.DEPARTMENT_ID
	JOIN LOCATIONS l 
	ON
		d.LOCATION_ID = l.LOCATION_ID
	JOIN COUNTRIES c 
	ON
		l.COUNTRY_ID = c.COUNTRY_ID
	JOIN REGIONS r 
	ON
		c.REGION_ID = r.REGION_ID
	GROUP BY
		r.REGION_NAME
	ORDER BY
		AVG(e.SALARY) DESC 
	) gg
WHERE rownum = 1;

