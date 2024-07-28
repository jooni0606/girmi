import json
import pathlib

import airflow
import requests
import requests.exceptions as requests_exceptions
from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator

from datetime import timedelta
import pendulum

dag = DAG(
    dag_id="download_rocket_launches",
    description="Download rocket pictures of recently launched rockets.",
    start_date=pendulum.today('UTC').subtract(days=1),
    schedule_interval=timedelta(days=1)
)

now_path = BashOperator(
    task_id="now_path",
    bash_command="ls -l /tmp",
    dag=dag
)

download_launches = BashOperator(
    task_id="download_launches",
    bash_command="curl -o /Users/joonhyounglee/Develop/data_temp/launches.json -L 'https://ll.thespacedevs.com/2.0.0/launch/upcoming'",  # noqa: E501
    dag=dag,
)


def _get_pictures():
    # Ensure directory exists
    pathlib.Path("/Users/joonhyounglee/Develop/data_temp/images").mkdir(parents=True, exist_ok=True)

    # Download all pictures in launches.json
    with open("/Users/joonhyounglee/Develop/data_temp/launches.json") as f:
        launches = json.load(f)
        image_urls = [launch["image"] for launch in launches["results"]]
        for image_url in image_urls:
            try:
                response = requests.get(image_url)
                image_filename = image_url.split("/")[-1]
                target_file = f"/Users/joonhyounglee/Develop/data_temp/images/{image_filename}"
                with open(target_file, "wb") as f:
                    f.write(response.content)
                print(f"Downloaded {image_url} to {target_file}")
            except requests_exceptions.MissingSchema:
                print(f"{image_url} appears to be an invalid URL.")
            except requests_exceptions.ConnectionError:
                print(f"Could not connect to {image_url}.")


get_pictures = PythonOperator(
    task_id="get_pictures", python_callable=_get_pictures, dag=dag
)

notify = BashOperator(
    task_id="notify",
    bash_command='echo "There are now $(ls /Users/joonhyounglee/Develop/data_temp/images/ | wc -l) images."',
    dag=dag,
)

now_path >> download_launches >> get_pictures >> notify