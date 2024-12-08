import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router, RouterModule } from '@angular/router';
import { EnrollmentService } from '../../service/enrollmentservice.service';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterModule],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {
  courses: any[] = [];
  errorMessage: string = '';
  // Directly specify the backend URL here
  constructor(private authService:AuthService,private r:Router,private enrollmentService:EnrollmentService,private http:HttpClient){}

  courseCount: number = 0;
  logout(): void {
    this.authService.logout();
    this.r.navigate(['/login']); // Redirect to login page
  }
  ngOnInit(): void {
    const userId = Number(localStorage.getItem('userId')); // Fetch userId from localStorage

    if (!userId) {
      this.errorMessage = 'User not logged in.';
      return;
    }
    console.log("before api ");
    console.log(userId);
    this.http.get<any[]>(`http://localhost:8091/api/enrollments/courses/${userId}`).subscribe({
      next: (data) => {
        console.log('Data:', data);
        this.courses = data; // Assign data to the courses array
        // console.log(this.courses);
        // this.courses = data; // Assign data to the courses array
        this.courseCount = this.courses.length; // Count the number of courses
        console.log('Course Count:', this.courseCount); // Debugging the course count
        this.enrollmentService.setCourseCount(this.courseCount);
      },
      error: (err) => {
        console.error('Error:', err);
        this.errorMessage = 'Failed to load courses.';
      },
    });
  }
  


}
