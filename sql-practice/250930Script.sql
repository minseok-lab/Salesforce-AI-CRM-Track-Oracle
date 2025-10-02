-- 담당 매니저가 배정되어있으나 커미션비율이 없고, 
-- 월급이 3000초과인 직원의 
-- 이름, 매니저아이디, 커미션 비율, 월급 을 출력하세요.(45명)

SELECT
	e.FIRST_NAME,
	e.MANAGER_ID ,
	e.COMMISSION_PCT ,
	e. SALARY 
FROM EMPLOYEES e 
WHERE e.MANAGER_ID IS NOT NULL
AND e.COMMISSION_PCT IS NULL
AND e.SALARY > 3000;


-- 최고월급이 10000이상인
-- 업무의 이름과 최고 월급을
-- 최고월급의 내림차순으로 정렬하려 출력하세요

SELECT j.JOB_TITLE , j.MAX_SALARY 
FROM JOBS j 
WHERE j.MAX_SALARY >= 10000
ORDER BY j.MAX_SALARY DESC;


-- 월급이 14000 미만 10000 이상인 직원의 이름, 월급, 커미션퍼센트를 월급순(내림차순) 츨력하세요
-- 단, 커미션퍼센트가 null이면 0으로 나타내시오

SELECT 
	e.FIRST_NAME ,
	e.SALARY ,
	NVL(e.COMMISSION_PCT, 0)
FROM EMPLOYEES e 
WHERE 10000 <= e.SALARY AND e.SALARY < 14000
ORDER BY e.SALARY DESC;


-- 부서번호가 10, 90, 100인 직원의 이름, 월급, 입사일, 부서번호를 나타내시오
-- 입사일은 1977-12와 같이 표시하시오


-- 이름에 S또는 s가 들어가는 직원의 이름, 월급을 나타내시오

SELECT
	e.FIRST_NAME AS 이름,
	e.SALARY AS 월급
FROM EMPLOYEES e 
WHERE e.FIRST_NAME LIKE 'S%' 
OR e.FIRST_NAME LIKE '%s%';
-- WHERE upper(e.FIRST_NAME) LIKE '%S%'


--
SELECT COUNT(*)
FROM EMPLOYEES e 
WHERE e.SALARY > 16000;

--
SELECT COUNT(*), SUM(e.SALARY )
FROM EMPLOYEES e 
WHERE e.SALARY > 16000;

--
SELECT
	COUNT(*) AS "총 직원 수",
	SUM(e.SALARY ) AS " 총 급여",
	AVG(NVL(e.SALARY , 0)) AS "평균 급여"
FROM EMPLOYEES e ;


-- 커미션 비율이 있는 사람들의 수, 커미션의 총합, 커미션 비율의 평균
SELECT 
	COUNT(e.COMMISSION_PCT ) AS "커미션 인원 수",
	SUM(e.COMMISSION_PCT) AS "커미션 총합", 
	ROUND( AVG( NVL( e.COMMISSION_PCT, 0 ) ), 3 ) AS "커미션 비율의 평균"
FROM EMPLOYEES e 
WHERE e.COMMISSION_PCT IS NOT NULL;


--
SELECT
	COUNT(*) AS "전체 인원 수",
	MAX(e.SALARY ) AS "최고급여",
	MIN(e.SALARY ) AS "최저급여"
FROM EMPLOYEES e;


--
SELECT
	e.DEPARTMENT_ID AS 부서번호,
	ROUND(AVG(e.SALARY ),2) AS 평균연봉
FROM EMPLOYEES e 
GROUP BY e.DEPARTMENT_ID 
ORDER BY e.DEPARTMENT_ID ASC;


-- 급여의 합계가 20000 초과한 부서의 부서 번호와, 인원수, 급여합계를 출력하세요
SELECT
	e.DEPARTMENT_ID AS "부서번호",
	COUNT(*) AS "총인원",
	SUM(e.SALARY ) AS "총급여"
FROM EMPLOYEES e 
-- WHERE "총급여" > 20000
WHERE e.DEPARTMENT_ID = 100 OR 110OR;

SELECT
	e.DEPARTMENT_ID AS "부서번호",
	COUNT(*) AS "총인원",
	SUM(e.SALARY ) AS "총급여"
FROM EMPLOYEES e 
GROUP BY e.DEPARTMENT_ID 
HAVING SUM(e.SALARY ) > 20000
ORDER BY 3 DESC;

SELECT
	e.DEPARTMENT_ID AS "부서번호",
	COUNT(*) AS "총 인원",
	SUM(e.SALARY ) AS "총 급여"
FROM EMPLOYEES e 
GROUP BY e.DEPARTMENT_ID 
HAVING SUM(e.SALARY ) > 20000
AND e.DEPARTMENT_ID = 100;

SELECT *
FROM EMPLOYEES e; 


--
SELECT
	e.EMPLOYEE_ID ,
	e.SALARY ,
	CASE WHEN job_id = 'AC_ACCOUNT' THEN salary + salary * 0.1
		 WHEN job_id = 'AC_MGR' THEN salary + salary * 0.2
		 ELSE salary
	END job_id
FROM EMPLOYEES e ;


-- 직원의 이름, 부서코드, 팀을 출력하세요
-- 팀은 부서코드로 결정하며 부서코드가 10~50이면 'A-TEAM', 60~100이면 'B-TEAM', 110~150이면 'C-TEAM' 나머지는 '팀없음'으로 출력하세요

SELECT
	e.FIRST_NAME AS 이름,
	e.DEPARTMENT_ID AS 부서코드,
	CASE WHEN e.DEPARTMENT_ID BETWEEN 10 AND 50 THEN 'A-TEAM'
		 WHEN e.DEPARTMENT_ID BETWEEN 60 AND 100 THEN 'B-TEAM'
		 WHEN e.DEPARTMENT_ID BETWEEN 110 AND 150 THEN 'C-TEAM'
		 ELSE '팀없음'
	END 팀명
FROM EMPLOYEES e;


-- 직원의 이름, 직급아이디, 직급명칭으 출력하세요
-- Join 할 테이블 (Employees, Jobs)

SELECT 
	e.FIRST_NAME || ' ' || e.LAST_NAME AS 이름,
	j.JOB_ID AS 직급,
	j.JOB_TITLE AS 직급명칭
FROM EMPLOYEES e, JOBS j 
WHERE e.JOB_ID = j.JOB_ID ;

SELECT 
	e.FIRST_NAME || ' ' || e.LAST_NAME AS 이름,
	j.JOB_ID AS 직급,
	j.JOB_TITLE AS 직급명칭
FROM EMPLOYEES e
JOIN JOBS j ON e.JOB_ID = j.JOB_ID;


-- 모든 직원이름, 부서이름, 업무명을 출력하세요
SELECT 
	e.FIRST_NAME || ' ' || e.LAST_NAME AS 이름,
	d.DEPARTMENT_NAME AS 부서이름,
	j.JOB_TITLE AS 업무명
FROM EMPLOYEES e , DEPARTMENTS d , JOBS j  
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
AND e.JOB_ID = j.JOB_ID ;


-- 사원과 그 매니저이름 조회
SELECT
	emp.FIRST_NAME AS 직원명,
	mgr.FIRST_NAME AS 매니저명
FROM EMPLOYEES emp, EMPLOYEES mgr 
WHERE emp.MANAGER_ID = mgr.EMPLOYEE_ID ;


--
SELECT 
	e.DEPARTMENT_ID ,
	e.FIRST_NAME ,
	d.DEPARTMENT_NAME 
FROM EMPLOYEES e 
LEFT OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID ;

--
SELECT 
	e.DEPARTMENT_ID ,
	e.FIRST_NAME ,
	d.DEPARTMENT_NAME 
FROM EMPLOYEES e, DEPARTMENTS d 
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID(+) ;

--
SELECT 
	e.DEPARTMENT_ID ,
	e.FIRST_NAME ,
	d.DEPARTMENT_NAME 
FROM EMPLOYEES e 
RIGHT OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID ;

--
SELECT 
	e.DEPARTMENT_ID ,
	e.FIRST_NAME ,
	d.DEPARTMENT_NAME 
FROM EMPLOYEES e, DEPARTMENTS d 
WHERE e.DEPARTMENT_ID(+) = d.DEPARTMENT_ID ;

--
SELECT 
	e.DEPARTMENT_ID ,
	e.FIRST_NAME ,
	d.DEPARTMENT_NAME 
FROM EMPLOYEES e 
FULL OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID ;

----------------------
------- 연습문제 -------
----------------------

-- 문제1
-- 각 사원에 대해서 사번, 이름, 부서명, 매니저의 이름을 조회하세요

SELECT
	emp.EMPLOYEE_ID AS 사번,
	emp.FIRST_NAME AS 이름,
	d.DEPARTMENT_NAME AS 부서명,
	mgr.FIRST_NAME AS 매니저명
FROM EMPLOYEES emp 
JOIN DEPARTMENTS d 
ON emp.DEPARTMENT_ID = d.DEPARTMENT_ID 
JOIN EMPLOYEES mgr
ON emp.MANAGER_ID = mgr.EMPLOYEE_ID
ORDER BY emp.EMPLOYEE_ID ASC;


-- 문제2
-- 지역에 속한 나라들을 지역이름, 나라이름으로 출력하되, 지역이름 나라이름 순서대로 내림차순으로 정렬하세요

SELECT
	r.REGION_NAME AS 지역이름,
	c.COUNTRY_NAME AS 나라이름
FROM COUNTRIES c 
JOIN REGIONS r 
ON c.REGION_ID = r.REGION_ID 
ORDER BY 1 DESC, 2 DESC;


-- 문제3
-- 각 부서(department)에 대해서 부서번호(department_id), 부서이름(department_name), 매니저(manager)의 이름(first_name),
-- 위치(location)한 도시(city), 나라(contries)의 이름(contries_name), 그리고 지역 구분(reigin)의 이름(reigin_name)까지 전부 출력

SELECT 
	d.DEPARTMENT_ID AS 부서번호,
	d.DEPARTMENT_NAME AS 부서이름,
	e.FIRST_NAME AS "매니저 이름",
	l.CITY AS "위치한 도시",
	c.COUNTRY_NAME AS "나라 이름",
	r.REGION_NAME AS "지역이름"
FROM DEPARTMENTS d 
JOIN EMPLOYEES e
ON d.MANAGER_ID = e.EMPLOYEE_ID 
JOIN LOCATIONS l 
ON d.LOCATION_ID = l.LOCATION_ID 
JOIN COUNTRIES c 
ON l.COUNTRY_ID = c.COUNTRY_ID 
JOIN REGIONS r 
ON c.REGION_ID = r.REGION_ID 
ORDER BY d.DEPARTMENT_ID ASC;

SELECT 
	d.DEPARTMENT_ID AS 부서번호,
	d.DEPARTMENT_NAME AS 부서이름,
	e.FIRST_NAME AS "매니저 이름",
	l.CITY AS "위치한 도시",
	c.COUNTRY_NAME AS "나라 이름",
	r.REGION_NAME AS "지역이름"
FROM DEPARTMENTS d , EMPLOYEES e, LOCATIONS l , COUNTRIES c , REGIONS r 
WHERE d.MANAGER_ID = e.EMPLOYEE_ID 
AND d.LOCATION_ID = l.LOCATION_ID 
AND l.COUNTRY_ID = c.COUNTRY_ID 
AND c.REGION_ID = r.REGION_ID 
ORDER BY d.DEPARTMENT_ID ASC;


-- 문제4
-- Public Accountant의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요
-- 현재 Public Accountant의 직책으로 근무하는 사원은 고려하지 않습니다.
-- 이름은 first_name과 last_name을 합쳐 출력합니다.

SELECT 
	e.EMPLOYEE_ID AS 사번,
	e.FIRST_NAME || ' ' || e.LAST_NAME AS 이름
FROM JOB_HISTORY jh 
JOIN JOBS j 
ON jh.JOB_ID = j.JOB_ID 
JOIN EMPLOYEES e 
ON jh.EMPLOYEE_ID = e.EMPLOYEE_ID
WHERE j.JOB_TITLE = 'Public Accountant'
AND jh.END_DATE IS NOT NULL;

--

SELECT 
	e.EMPLOYEE_ID AS 사번,
	e.FIRST_NAME || ' ' || e.LAST_NAME AS 이름
FROM JOB_HISTORY jh , JOBS j , EMPLOYEES e 
WHERE jh.JOB_ID = j.JOB_ID 
AND jh.EMPLOYEE_ID = e.EMPLOYEE_ID
AND j.JOB_TITLE = 'Public Accountant'
AND jh.END_DATE IS NOT NULL;


-- 문제5 (숙제1)
-- 직원들의 사번(employee_id), 이름(first_name), 성(last_name)과 부서 이름(department_name)을 조회하여 성(last_name)순서로 오름차순 정렬하세요.
-- 부서가 없는 사람도 조회되도록. 107명

SELECT 
	e.EMPLOYEE_ID AS 사번,
	e.FIRST_NAME AS 성,
	e.LAST_NAME AS 이름,
	d.DEPARTMENT_NAME AS "부서 이름"
FROM EMPLOYEES e 
LEFT OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
ORDER BY e.FIRST_NAME ASC;

SELECT 
	e.EMPLOYEE_ID AS 사번,
	e.FIRST_NAME AS 성,
	e.LAST_NAME AS 이름,
	d.DEPARTMENT_NAME AS "부서 이름"
FROM
	EMPLOYEES e,
	DEPARTMENTS d 
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID(+)
ORDER BY 3;


-- 문제6 (숙제2)
-- 자신의 매니저보다 채용일(hire_date)이 빠른 사원의 사번(employee_id), 성(last_name)과 채용일(hire_date)을 조회하세요.
-- 37명

SELECT 
	emp.EMPLOYEE_ID AS 사번,
	emp.LAST_NAME AS 성,
	emp.HIRE_DATE AS 채용일
FROM EMPLOYEES emp 
JOIN EMPLOYEES mgr
ON emp.MANAGER_ID = mgr.EMPLOYEE_ID 
WHERE emp.HIRE_DATE < mgr.HIRE_DATE 
ORDER BY emp.EMPLOYEE_ID ASC;

SELECT
	emp.EMPLOYEE_ID AS 사번,
	emp.LAST_NAME AS 성,
	emp.HIRE_DATE AS 채용일
FROM
	EMPLOYEES emp ,
	EMPLOYEES mgr
WHERE emp.MANAGER_ID = mgr.EMPLOYEE_ID 
AND  emp.HIRE_DATE < mgr.HIRE_DATE;